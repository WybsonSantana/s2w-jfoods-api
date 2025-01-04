package br.dev.s2w.jfoods.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) //, reason = "Entity not foud")
public class EntityNotFoundException extends ResponseStatusException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }

    public EntityNotFoundException(String message) {
        this(HttpStatus.NOT_FOUND, message);
    }
}
