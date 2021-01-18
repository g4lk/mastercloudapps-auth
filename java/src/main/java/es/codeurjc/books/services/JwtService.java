package es.codeurjc.books.services;

import es.codeurjc.books.dtos.responses.UserResponseDto;

public interface JwtService {

    public UserResponseDto getUser(String authHeader);
}
