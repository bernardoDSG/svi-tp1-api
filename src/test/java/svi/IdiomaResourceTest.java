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

import svi.dto.IdiomaDTO;
import svi.dto.IdiomaDTOResponse;

import svi.service.IdiomaService;
@QuarkusTest
public class IdiomaResourceTest {
    
    @Inject
    IdiomaService idiomaservice;

    @Test
    void buscarTodosTest() {
             RestAssured.given()
                .when()
                    .get("/idiomas")
                .then()
                    .statusCode(200);    
    }

    @Test
    void buscarPorIdTest() {
            RestAssured.given()
                .when()
                    .get("/idiomas/1")
                .then()
                    .statusCode(200);
    }

    @Test
    void buscarPorNomeTest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("nome", "Português do Brasil")
                .when()
                    .get("/idiomas/buscar")
                .then()
                    .statusCode(200)
                    .body("[0].nome", is("Português do Brasil"))
                    .body("[0].id", notNullValue()); 
    }

    @Test
    void buscarPorSiglaTest() {
        RestAssured.given()
                  .contentType(ContentType.JSON)
                  .queryParam("sigla", "pt-br")
                  .when()
                      .get("idiomas/sigla")
                  .then()
                      .statusCode(200)
                      .body("[0].sigla", is("pt-br"))
                      .body("[0].id", notNullValue());
    }

    @Test
    void incluirTest() {
        IdiomaDTO dto = new IdiomaDTO("Francês","fr");  
        
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                    .post("/idiomas")
                .then()
                    .statusCode(201)
                    .body("id", CoreMatchers.notNullValue(),
                                    "nome", CoreMatchers.is("Francês"),
                                    "sigla",CoreMatchers.is("fr"));
        
    }

    @Test
    void alterarTest() {
        IdiomaDTO dto = new IdiomaDTO("Espanhol", "esp");
        IdiomaDTOResponse response = idiomaservice.create(dto);
        IdiomaDTO dtoUpdate = new IdiomaDTO("Chinês", "ch");
        RestAssured.given()
                   .contentType(ContentType.JSON)
                   .body(dtoUpdate)
                   .when()
                        .put("/idiomas/"+response.id())
                   .then()
                        .statusCode(204);
        response = idiomaservice.findById(response.id());
        assertEquals(dtoUpdate.nome(), response.nome());
        assertEquals(dtoUpdate.sigla(), response.sigla());
    }

    @Test
    void apagarTest() {
        IdiomaDTO dto = new IdiomaDTO("Japonês", "jp");
        IdiomaDTOResponse response = idiomaservice.create(dto);

        RestAssured.given()
                .when()
                    .delete("/idiomas/"+response.id())
                .then()
                    .statusCode(204);
        response = idiomaservice.findById(response.id());

        assertNull(response);
    }
}
