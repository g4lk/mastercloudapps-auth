const express = require("express");
const router = express.Router();
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");
const config = require("../config/auth");
const { User } = require("../models/user.js");

router.post("/login", async (req, res) => {
  try {
    const user = await User.findOne({
      nick: req.body.nick,
    }).exec();

    if (!user) return res.status(404).send({ error: "User not found" });

    const match = await bcrypt.compareSync(req.body.password, user.password);

    if (!match) return res.status(401).send({ error: "Invalid credentials" });

    const token = jwt.sign({ id: user._id }, config.JWT_SECRET, {
      expiresIn: config.EXPIRES_IN,
    });

    res.json({
      token,
    });
  } catch (error) {
    console.log(error);
    res.status(400).send(error);
  }
});

module.exports = router;