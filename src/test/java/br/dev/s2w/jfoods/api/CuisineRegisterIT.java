package br.dev.s2w.jfoods.api;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CuisineRegisterIT {

    @LocalServerPort
    private int port;

    @Test
    public void shouldReturnStatus200WhenQueryingCuisines() {
        enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .basePath("/cuisines")
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldContain4CuisinesWhenQueryingCuisines() {
        enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .basePath("/cuisines")
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", hasSize(4))
                .body("name", Matchers.hasItems("Indiana", "Tailandesa", "Argentina", "Brasileira"));
    }

}
