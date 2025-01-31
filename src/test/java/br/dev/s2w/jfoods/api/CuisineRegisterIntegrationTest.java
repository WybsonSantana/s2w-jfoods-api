package br.dev.s2w.jfoods.api;

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
public class CuisineRegisterIntegrationTest {

    @Autowired
    private CuisineRegisterService cuisineRegister;

    @Test
    public void testCuisineRegistrationSuccessfully() {
// scenario
        Cuisine newCuisine = new Cuisine();
        newCuisine.setName("Chinesa");

        // action
        newCuisine = cuisineRegister.save(newCuisine);

// validation
        assertThat(newCuisine.getId()).isNotNull();
    }

    @Test
    public void testCuisineRegistrationWithoutName() {
        // scenario
        Cuisine newCuisine = new Cuisine();
        newCuisine.setName(null);

        // action
        ConstraintViolationException expectedError = assertThrows(ConstraintViolationException.class, () -> {
            cuisineRegister.save(newCuisine);
        });

        // validation
        assertThat(expectedError).isNotNull();
    }

}
