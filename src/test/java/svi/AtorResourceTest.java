package svi;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.List;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import svi.converter.ConverterPremioString;
import svi.dto.AtorDTO;
import svi.model.Premio;
import svi.service.AtorService;


@QuarkusTest
public class AtorResourceTest {
    
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
        ConverterPremioString converter = new ConverterPremioString();
        List<Premio> premios = converter.convertToEntityAttribute("Oscar,Globo de Ouro,British Academy Film Awards");
        
        AtorDTO dto = new AtorDTO("Christian Bale",premios);  
        
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
}
