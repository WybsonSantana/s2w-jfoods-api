package br.dev.s2w.jfoods.api.domain.exception;

public class EntityInUseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityInUseException(String message) {
        super(message);
    }
}
