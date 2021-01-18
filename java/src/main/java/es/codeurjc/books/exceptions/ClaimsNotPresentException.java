package es.codeurjc.books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Claims not present in jwt")
public class ClaimsNotPresentException extends RuntimeException {

    private static final long serialVersionUID = 6600084908618284957L;
}
