package svi;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import svi.dto.PoltronaDTO;
import svi.dto.PoltronaDTOResponse;
import svi.service.PoltronaService;

@QuarkusTest
public class PoltronaResourceTest {

    @Inject
    PoltronaService service;

    @Test
    void buscarTodosTest() {
             RestAssured.given()
                .when()
                    .get("/poltronas")
                .then()
                    .statusCode(200);    
    }

    @Test
    void buscarPorIdTest() {
            RestAssured.given()
                .when()
                    .get("/poltronas/1")
                .then()
                    .statusCode(200);
    }

    @Test
    void buscarPorNomeTest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("nome", "A1")
                .when()
                    .get("/poltronas/buscar")
                .then()
                    .statusCode(200)
                    .body("[0].nome", is("A1"))
                    .body("[0].id", notNullValue());
    }

    @Test
    void buscarPorDisponibilidadeTest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("estaOcupada", true)
                .when()
                    .get("/poltronas/disponibilidade")
                .then()
                    .statusCode(200)
                    .body("[0].estaOcupada", is(true))
                    .body("[0].id", notNullValue());
    }

    @Test
    void incluirTest() {
        PoltronaDTO dto = new PoltronaDTO("B1", true);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                    .post("/poltronas")
                .then()
                    .statusCode(201)
                    .body("id", CoreMatchers.notNullValue(),
                          "nome",CoreMatchers.is("B1"),
                          "estaOcupada",CoreMatchers.is(true));
    }

    @Test
    void atualizarTest() {
        PoltronaDTO dto = new PoltronaDTO("B2", false);
        PoltronaDTOResponse response = service.create(dto);
        PoltronaDTO dtoUpdate = new PoltronaDTO("B3", true);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when()
                    .put("/poltronas/"+response.id())
                .then()
                    .statusCode(204);
        response = service.findById(response.id());
        assertEquals(dtoUpdate.nome(),response.nome());
        assertEquals(dtoUpdate.estaOcupada(), response.estaOcupada());
    }

    @Test
    void apagarTest() {
        PoltronaDTO dto = new PoltronaDTO("C2", true);
        PoltronaDTOResponse response = service.create(dto);
        RestAssured.given()
                .when()
                    .delete("/poltronas/"+response.id())
                .then()
                    .statusCode(204);
        response = service.findById(response.id());
        assertNull(response);
    }
}
