const config = require("../config/auth");
const jwt = require("jsonwebtoken");
const { User } = require("../models/user");

const verify = (req, res, next) => {
    const token = extractToken(req);

    if (!token) return res.status(401).send({auth: false, message: "There is no token. "});

    jwt.verify(token, config.JWT_SECRET, (jwtErr, decoded) => {
        if (jwtErr) return res.status(500).send({auth: false, message: "Failed to verify token."});

        User.findById(decoded.id, { password: 0 }, (dbError, user) => {
            if (dbError) return res.status(500).send({auth: false, message: "Failed to find user associated to the token."});
            if (!user) return res.status(401).send({auth: false, message: "This user has no token"});
            next();
        });
    });
};

const extractToken = (req) => {
    return req.header('Authorization')?req.header('Authorization').replace('Bearer ', ''):null;
}

module.exports = {
    verify
};