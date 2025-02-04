package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import br.dev.s2w.jfoods.api.domain.repository.RestaurantRepository;
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
public class RestaurantControllerIT extends GeneralUtils {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private Flyway flyway;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private static final String RESTAURANT_ID_PARAM = "restaurantId";
    private static final String RESTAURANT_ID_MAPPING = "/{restaurantId}";
    private static final String NAME_ATTRIBUTE = "name";
    private static final Long EXISTENT_AND_IN_USE_BURGER_TOP_RESTAURANT_ID = 2L;
    private static final Long NON_EXISTENT_RESTAURANT_ID = 100L;

    @BeforeEach
    public void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        port = serverPort;
        basePath = "/restaurants";

        flyway.migrate();
    }

    @Test
    public void shouldReturnStatus200WhenQueryingRestaurants() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnCorrectQuantityWhenQueryingRestaurants() {
        int correctNumberOfRegisteredRestaurants = ((int) restaurantRepository.count());

        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", hasSize(correctNumberOfRegisteredRestaurants));
    }

    @Test
    public void shouldReturnCorrectResponseAndStatusWhenQueryingExistingRestaurant() {
        Restaurant burgerTopRestaurant = restaurantRepository
                .findById(EXISTENT_AND_IN_USE_BURGER_TOP_RESTAURANT_ID).orElseThrow();

        given()
                .pathParam(RESTAURANT_ID_PARAM, EXISTENT_AND_IN_USE_BURGER_TOP_RESTAURANT_ID)
                .accept(ContentType.JSON)
                .when()
                .get(RESTAURANT_ID_MAPPING)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(NAME_ATTRIBUTE, equalTo(burgerTopRestaurant.getName()));
    }

    @Test
    public void shouldReturnStatus201WhenRegisteringRestaurant() {
        String newYorkBarbecueRestaurantInputJson = super
                .getContentFromResource("/payload/input/restaurant/new-york-barbecue.json");

        given()
                .body(newYorkBarbecueRestaurantInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturnStatus200WhenUpdatingRestaurant() {
        String burgerTopRestaurantWithDeliveryFeeUpdateInputJson = super
                .getContentFromResource("/payload/input/restaurant/burger-top-with-delivery-fee-update.json");

        given()
                .pathParam("restaurantId", EXISTENT_AND_IN_USE_BURGER_TOP_RESTAURANT_ID)
                .body(burgerTopRestaurantWithDeliveryFeeUpdateInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put(RESTAURANT_ID_MAPPING)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnStatus404WhenQueryingNonExistentRestaurant() {
        given()
                .pathParam(RESTAURANT_ID_PARAM, NON_EXISTENT_RESTAURANT_ID)
                .accept(ContentType.JSON)
                .when()
                .get(RESTAURANT_ID_MAPPING)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldReturnStatus404WhenUpdatingNonExistentRestaurant() {
        String burgerTopRestaurantWithDeliveryFeeUpdateInputJson = super
                .getContentFromResource("/payload/input/restaurant/burger-top-with-delivery-fee-update.json");

        given()
                .pathParam("restaurantId", NON_EXISTENT_RESTAURANT_ID)
                .body(burgerTopRestaurantWithDeliveryFeeUpdateInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put(RESTAURANT_ID_MAPPING)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldReturnStatus400WhenRegisteringRestaurantWithoutName() {
        String newYorkBarbecueRestaurantWithoutNameInputJson = super
                .getContentFromResource("/payload/input/restaurant/new-york-barbecue-without-name.json");

        given()
                .body(newYorkBarbecueRestaurantWithoutNameInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturnStatus400WhenRegisteringRestaurantWithoutDeliveryFee() {
        String newYorkBarbecueRestaurantWithoutDeliveryFeeInputJson = super
                .getContentFromResource("/payload/input/restaurant/new-york-barbecue-without-delivery-fee.json");

        given()
                .body(newYorkBarbecueRestaurantWithoutDeliveryFeeInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturnStatus400WhenRegisteringRestaurantWithDeliveryFeeLessThanZero() {
        String newYorkBarbecueRestaurantWithDeliveryFeeLessThanZeroInputJson = super
                .getContentFromResource("/payload/input/restaurant/new-york-barbecue-with-delivery-fee-less-than-zero.json");

        given()
                .body(newYorkBarbecueRestaurantWithDeliveryFeeLessThanZeroInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturnStatus400WhenRegisteringRestaurantWithoutCuisine() {
        String newYorkBarbecueRestaurantWithoutCuisineInputJson = super
                .getContentFromResource("/payload/input/restaurant/new-york-barbecue-without-cuisine.json");

        given()
                .body(newYorkBarbecueRestaurantWithoutCuisineInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturnStatus400WhenRegisteringRestaurantWithoutCuisineId() {
        String newYorkBarbecueRestaurantWithoutCuisineIdInputJson = super
                .getContentFromResource("/payload/input/restaurant/new-york-barbecue-without-cuisine-id.json");

        given()
                .body(newYorkBarbecueRestaurantWithoutCuisineIdInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturnStatus400WhenRegisteringRestaurantWithNonExistentCuisine() {
        String newYorkBarbecueRestaurantWithNonExistentCuisineInputJson = super
                .getContentFromResource("/payload/input/restaurant/new-york-barbecue-with-non-existent-cuisine.json");

        given()
                .body(newYorkBarbecueRestaurantWithNonExistentCuisineInputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
