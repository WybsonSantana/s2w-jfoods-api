package br.dev.s2w.jfoods.api.domain.exception;

public class CuisineNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CuisineNotFoundException(String message) {
        super(message);
    }

    public CuisineNotFoundException(Long cuisineId) {
        this(String.format("There is no cuisine registration with the code %d", cuisineId));
    }

}
