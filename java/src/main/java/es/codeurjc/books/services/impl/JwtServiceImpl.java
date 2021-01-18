package es.codeurjc.books.services.impl;

import es.codeurjc.books.dtos.responses.UserResponseDto;
import es.codeurjc.books.exceptions.InvalidJwtTokenException;
import es.codeurjc.books.services.JwtService;
import es.codeurjc.books.services.UserService;
import org.springframework.stereotype.Service;


import static es.codeurjc.books.utils.JwtUtils.*;

@Service
public class JwtServiceImpl implements JwtService {

    private UserService userService;

    public JwtServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserResponseDto getUser(String authHeader) {
            boolean valid = checkValid(authHeader);
            String jwt = getJwt(authHeader).orElseThrow(InvalidJwtTokenException::new);
            if (valid) {
                String nick = getSubject(jwt);
                return this.userService.findByNick(nick);
            }
            else {
                throw new InvalidJwtTokenException();
            }
        }

}
