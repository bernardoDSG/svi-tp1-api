package svi;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.inject.Inject;
import svi.service.FilmeService;

@QuarkusTest
public class FilmeResourceTest {

    @Inject
    FilmeService filmeService;

    @Test
    public void buscarTodosTest() {
        RestAssured.given()
            .when()
                .get("/filmes")
            .then()
                .statusCode(200);    
    }

    @Test
    public void buscarPorIdTest() {
        RestAssured.given()
            .when()
                .get("/filmes/1")
            .then()
                .statusCode(200);
    }

    
}
