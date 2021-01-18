package es.codeurjc.books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Not Authorization header present in request")
public class NotAuthorizationHeaderPresentException extends RuntimeException {

    private static final long serialVersionUID = 6600084908618284957L;
}

