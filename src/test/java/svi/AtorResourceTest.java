package svi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;



import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import svi.converter.ConverterPremioString;
import svi.dto.AtorDTO;
import svi.dto.AtorDTOResponse;


import svi.service.AtorService;


@QuarkusTest
public class AtorResourceTest {
    ConverterPremioString converter = new ConverterPremioString();
    @Inject
    AtorService atorservice;

    @Test
    void buscarTodosTest() {
        RestAssured.given()
                .when()
                    .get("/atores")
                .then()
                    .statusCode(200);    
    }

    @Test
    void buscarPorIdTest() {
        RestAssured.given()
                .when()
                    .get("/atores/1")
                .then()
                    .statusCode(200);
    }

    @Test
    void buscarPorNomeTest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("nome", "Brad Pitt")
                .when()
                    .get("/atores/buscar")
                .then()
                    .statusCode(200)
                    .body("[0].nome", is("Brad Pitt"))
                    .body("[0].id", notNullValue());
    }

    
    /*
    @Test
    void incluirTest() {
        
        
        
        AtorDTO dto = new AtorDTO("Christian Bale","Oscar,Globo de Ouro,British Academy Film Awards");  
        
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                    .post("/atores")
                .then()
                    .statusCode(201)
                    .body("id", CoreMatchers.notNullValue(),
                                    "nome", CoreMatchers.is("Christian Bale"),
                                    "premios",CoreMatchers.hasItems("Oscar","Globo de Ouro","British Academy Film Awards"));
        
    }  
    @Test
    void alterarTest() {
        
        
        AtorDTO dto = new AtorDTO("Robert Downey Jr","Oscar,British Academy Film Awards");
        AtorDTOResponse response = atorservice.create(dto);
        
        AtorDTO dtoUpdate = new AtorDTO("Robert Downey Jr", "Oscar,Globo de Ouro");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when()
                    .put("/atores/"+response.id())
                .then()
                    .statusCode(204);
        
        response = atorservice.findById(response.id());
        assertEquals(dtoUpdate.nome(), response.nome());
        assertEquals(dtoUpdate.premios(),response.premios());


    }
         @Test
    void apagarTest() {
        
        
        AtorDTO dto = new AtorDTO("Cilian Murphy", "Oscar,Leão de Ouro(Festival de Veneza)");

        AtorDTOResponse response = atorservice.create(dto);

        RestAssured.given()
                .when()
                    .delete("/atores/"+response.id())
                .then()
                    .statusCode(204);

        response = atorservice.findById(response.id());

        assertNull(response);
    }*/
   

   

}
