package svi;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.util.List;

import org.hamcrest.CoreMatchers;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import jakarta.inject.Inject;
import svi.dto.PoltronaDTO;
import svi.dto.SalaDTO;
import svi.dto.SalaDTOResponse;
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
                .queryParam("nome", "Sala Refresh")
                .when()
                    .get("/salas/buscar")
                .then()
                    .statusCode(200)
                    .body("[0].nome", is("Sala Refresh"))
                    .body("[0].id", notNullValue());
    }

    @Test
    void incluirTest() {
        SalaDTO dto = new SalaDTO("Sala Kinoplex", List.<@NonNull PoltronaDTO>of(new PoltronaDTO("K1"), new PoltronaDTO("K2")));
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                    .post("/salas")
                .then()
                    .statusCode(201)
                    .body("id", CoreMatchers.notNullValue(),
                          "nome", is("Sala Kinoplex"),
                          "poltronas[0].nome", is("K1"),
                          "poltronas[1].nome", is("K2"));
    }

    @Test
    void atualizarTest() {
        SalaDTO salaDto = new SalaDTO("Sala Velopex", List.<@NonNull PoltronaDTO>of(new PoltronaDTO("V1"), new PoltronaDTO("V2")));
        SalaDTOResponse response = salaservice.create(salaDto);
        SalaDTO dtoUpdate = new SalaDTO("Sala Pokemon", List.<@NonNull PoltronaDTO>of(new PoltronaDTO("P1"), new PoltronaDTO("P2")));
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when()
                    .put("/salas/"+response.id())
                .then()
                    .statusCode(204);
        
        response = salaservice.findById(response.id());

        assertEquals(dtoUpdate.nome(), response.nome());
        assertEquals(dtoUpdate.poltronas().get(0).nome(), response.poltronas().get(0).nome());
        assertEquals(dtoUpdate.poltronas().get(1).nome(), response.poltronas().get(1).nome());
    }

    @Test
    void apagarTest() {
        SalaDTO dto = new SalaDTO("Sala Daten", List.<@NonNull PoltronaDTO>of(new PoltronaDTO("D1"), new PoltronaDTO("D2")));
        SalaDTOResponse response = salaservice.create(dto);
        RestAssured.given()
                .when()
                    .delete("/salas/"+response.id())
                .then()
                    .statusCode(204);

        response = salaservice.findById(response.id());
        assertNull(response);
    }
    }
    