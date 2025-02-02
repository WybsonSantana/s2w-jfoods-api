package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import br.dev.s2w.jfoods.api.util.GeneralUtils;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CuisineControllerIT extends GeneralUtils {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private Flyway flyway;

    @Autowired
    private CuisineRepository cuisineRepository;

    @BeforeEach
    public void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        port = serverPort;
        basePath = "/cuisines";

        flyway.migrate();
    }

    @Test
    public void shouldReturnStatus200WhenQueryingCuisines() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnCorrectQuantityWhenQueryingCuisines() {
        int correctNumberOfRegisteredCuisines = ((int) cuisineRepository.count());

        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", hasSize(correctNumberOfRegisteredCuisines));
    }

    @Test
    public void shouldReturnStatus201WhenRegisteringCuisine() {
        String chineseCuisineInputJson = super.getContentFromResource("/payload/input/cuisine/chinesa.json");

        given()
                .body(chineseCuisineInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturnCorrectResponseAndStatusWhenQueryingExistingCuisine() {
        Long existentCuisineId = 2L;
        Cuisine americanCuisine = cuisineRepository.findById(existentCuisineId).orElseThrow();

        given()
                .pathParam("cuisineId", existentCuisineId)
                .accept(ContentType.JSON)
                .when()
                .get("/{cuisineId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(americanCuisine.getName()));
    }

    @Test
    public void shouldReturnStatus404WhenQueryingNonExistentCuisine() {
        Long nonExistentCuisineId = 100L;

        given()
                .pathParam("cuisineId", nonExistentCuisineId)
                .accept(ContentType.JSON)
                .when()
                .get("/{cuisineId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}
