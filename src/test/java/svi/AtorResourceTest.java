package svi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;



import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import svi.converter.ConverterIdObjeto;
import svi.converter.ConverterPremioString;
import svi.dto.AtorDTO;
import svi.dto.AtorDTOResponse;


import svi.service.AtorService;


@QuarkusTest
public class AtorResourceTest {
    ConverterPremioString converter = new ConverterPremioString();
    ConverterIdObjeto converterid = new ConverterIdObjeto();
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

    
    
    @Test
    void incluirTest() {
        
        
        List<Long> idsPremios = new ArrayList<>();
        idsPremios.add(1l);
        idsPremios.add(6l);
        idsPremios.add(5l);

        AtorDTO atorDTO = new AtorDTO("Dwayne Johnson",idsPremios);
        
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(atorDTO)
                .when()
                    .post("/atores")
                .then()
                    .statusCode(201)
                    .body("id", CoreMatchers.notNullValue(),
                                    "nome", CoreMatchers.is("Dwayne Johnson"),
                                    "premios",CoreMatchers.is("Oscar,Globo de Ouro,British Academy Film Awards"));
        
    }  
    @Test
    void alterarTest() {
        AtorDTO atorDTO = new AtorDTO("Jason Statham",  List.of(1l,2l));
        AtorDTOResponse response = atorservice.create(atorDTO);
        AtorDTO dtoUpdate = new AtorDTO("Murilo Benicio", List.of(2l,3l));
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when()
                    .put("/atores/"+response.id())
                .then()
                    .statusCode(204);
        
        response = atorservice.findById(response.id());

        assertEquals(dtoUpdate.nome(), response.nome());
        assertEquals(converter.convertToDatabaseColumn(dtoUpdate.idPremios().stream().map(i ->converterid.premioFromId(i)).toList()),response.premios());


    }
    @Test
    void apagarTest() {
        
        
        AtorDTO atorDTO = new AtorDTO("Robert Deniro", List.of(5l,6l));

        AtorDTOResponse response = atorservice.create(atorDTO);

        RestAssured.given()
                .when()
                    .delete("/atores/"+response.id())
                .then()
                    .statusCode(204);

        response = atorservice.findById(response.id());

        assertNull(response);
    }
   

   

}
