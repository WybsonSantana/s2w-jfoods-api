package br.dev.s2w.jfoods.api;

import br.dev.s2w.jfoods.api.domain.exception.EntityInUseException;
import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.service.CuisineRegisterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CuisineRegisterIT {

    @Autowired
    private CuisineRegisterService cuisineRegister;

    @Test
    public void shouldAssignIdWhenRegisteringCuisineWithCorrectData() {
        Cuisine newCuisine = new Cuisine();
        newCuisine.setName("Chinesa");

        newCuisine = cuisineRegister.save(newCuisine);

        assertThat(newCuisine.getId()).isNotNull();
    }

    @Test
    public void shouldFailWhenRegisteringCuisineWithoutName() {
        Cuisine newCuisine = new Cuisine();
        newCuisine.setName(null);

        assertThrows(ConstraintViolationException.class, () ->
                cuisineRegister.save(newCuisine));
    }

    @Test
    public void shouldFailWhenDeletingCuisineInUse() {
        Long cuisineId = 1L;

        assertThrows(EntityInUseException.class, () ->
                cuisineRegister.remove(cuisineId));
    }

    @Test
    public void shouldFailWhenDeletingCuisineNotFound() {
        Long cuisineId = 100L;

        assertThrows(EntityNotFoundException.class, () ->
                cuisineRegister.remove(cuisineId));
    }

}
