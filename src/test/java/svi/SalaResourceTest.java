package svi;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import svi.dto.PoltronaDTO;
import svi.dto.SalaDTO;
import svi.service.SalaService;

@QuarkusTest
public class SalaResourceTest {

    @Inject
    SalaService salaservice;

    @Test
    void buscarTodosTest() {
             RestAssured.given()
                .when()
                    .get("/salas")
                .then()
                    .statusCode(200);    
    }

    @Test
    void buscarPorIdTest() {
            RestAssured.given()
                .when()
                    .get("/salas/1")
                .then()
                    .statusCode(200);
    }

    @Test
    void buscarPorNomeTest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("nome", "Sala 1")
                .when()
                    .get("/salas/buscar")
                .then()
                    .statusCode(200)
                    .body("[0].nome", is("Sala 1"))
                    .body("[0].id", notNullValue());
    }

    @Test
    void incluirTest() {
        SalaDTO dto = new SalaDTO("Sala 4", null);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                    .post("/salas")
                .then()
                    .statusCode(201)
                    .body("id", CoreMatchers.notNullValue(),
                          "nome",CoreMatchers.is("B1"),
                          "estaOcupada",CoreMatchers.is(true));
    }
}
