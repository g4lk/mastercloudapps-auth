package es.codeurjc.books.utils;

import es.codeurjc.books.exceptions.ClaimsNotPresentException;
import es.codeurjc.books.exceptions.InvalidJwtTokenException;
import es.codeurjc.books.exceptions.NotAuthorizationHeaderPresentException;
import es.codeurjc.books.exceptions.UserNotFoundException;
import es.codeurjc.books.security.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.xml.bind.DatatypeConverter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

public class JwtUtils {

    public static boolean checkValid(String authHeader) {
        if (authHeader != null) {
            String jwt = getJwt(authHeader).orElseThrow(InvalidJwtTokenException::new);

            Claims claims = decodeJWT(jwt).orElseThrow(ClaimsNotPresentException::new);

            return LocalDateTime.now()
                        .isBefore(LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault()));
        }
        throw new NotAuthorizationHeaderPresentException();
    }

    public static Optional<String> getJwt(String authHeader) {
        String jwt = authHeader.substring(Constants.TOKEN_BEARER_PREFIX.length());
        if (jwt.equalsIgnoreCase(""))
            return Optional.empty();
        else
            return Optional.of(jwt);
    }


    private static Optional<Claims> decodeJWT(String jwt) {
        try {
            //Si no está firmado suelta excepción
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(Constants.SUPER_SECRET_KEY))
                    .parseClaimsJws(jwt).getBody();
            return Optional.ofNullable(claims);
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }

    public static String getSubject(String jwt) {
        Claims claims = decodeJWT(jwt).orElseThrow(UserNotFoundException::new);
        return claims.getSubject();
    }
}
