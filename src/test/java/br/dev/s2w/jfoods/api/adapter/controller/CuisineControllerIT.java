package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import br.dev.s2w.jfoods.api.domain.service.CuisineRegisterService;
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

    @Autowired
    private CuisineRegisterService cuisineRegister;

    private static final String CUISINE_ID_PARAM = "cuisineId";
    private static final String CUISINE_ID_MAPPING = "/{cuisineId}";
    private static final String NAME_ATTRIBUTE = "name";
    private static final Long EXISTENT_AND_IN_USE_AMERICAN_CUISINE_ID = 2L;
    private static final Long NON_EXISTENT_CUISINE_ID = 100L;

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
    public void shouldReturnCorrectResponseAndStatusWhenQueryingExistingCuisine() {
        Cuisine americanCuisine = cuisineRepository
                .findById(EXISTENT_AND_IN_USE_AMERICAN_CUISINE_ID).orElseThrow();

        given()
                .pathParam(CUISINE_ID_PARAM, EXISTENT_AND_IN_USE_AMERICAN_CUISINE_ID)
                .accept(ContentType.JSON)
                .when()
                .get(CUISINE_ID_MAPPING)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(NAME_ATTRIBUTE, equalTo(americanCuisine.getName()));
    }

    @Test
    public void shouldReturnStatus201WhenRegisteringCuisine() {
        String chineseCuisineInputJson = super
                .getContentFromResource("/payload/input/cuisine/chinese.json");

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
    public void shouldReturnStatus200WhenUpdatingCuisine() {
        String northAmericanCuisineInputJson = super
                .getContentFromResource("/payload/input/cuisine/north-american.json");

        given()
                .pathParam(CUISINE_ID_PARAM, EXISTENT_AND_IN_USE_AMERICAN_CUISINE_ID)
                .body(northAmericanCuisineInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put(CUISINE_ID_MAPPING)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnStatus204WhenDeletingCuisine() {
        Cuisine northAmericanCuisine = new Cuisine();
        northAmericanCuisine.setName("Norte Americana");

        northAmericanCuisine = cuisineRegister.save(northAmericanCuisine);

        given()
                .pathParam(CUISINE_ID_PARAM, northAmericanCuisine.getId())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete(CUISINE_ID_MAPPING)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void shouldReturnStatus404WhenQueryingNonExistentCuisine() {
        given()
                .pathParam(CUISINE_ID_PARAM, NON_EXISTENT_CUISINE_ID)
                .accept(ContentType.JSON)
                .when()
                .get("/{cuisineId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldReturnStatus404WhenDeletingNonExistentCuisine() {
        given()
                .pathParam(CUISINE_ID_PARAM, NON_EXISTENT_CUISINE_ID)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete(CUISINE_ID_MAPPING)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldReturnStatus404WhenUpdatingNonExistentCuisine() {
        String northAmericanCuisineInputJson = super
                .getContentFromResource("/payload/input/cuisine/north-american.json");

        given()
                .pathParam(CUISINE_ID_PARAM, NON_EXISTENT_CUISINE_ID)
                .body(northAmericanCuisineInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put(CUISINE_ID_MAPPING)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldReturnStatus400WhenRegisteringCuisineWithoutName() {
        String cuisineWithoutNameInputJson = super
                .getContentFromResource("/payload/input/cuisine/cuisine-without-name.json");

        given()
                .body(cuisineWithoutNameInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturnStatus409WhenDeletingCuisineInUse() {
        given()
                .pathParam(CUISINE_ID_PARAM, EXISTENT_AND_IN_USE_AMERICAN_CUISINE_ID)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete(CUISINE_ID_MAPPING)
                .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

}
