package svi;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;

import org.hamcrest.CoreMatchers;
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
        PoltronaDTO p1 = new PoltronaDTO("K5", true);
        PoltronaDTO p2 = new PoltronaDTO("L9", false);
        List<PoltronaDTO> poltronas = List.of(p1,p2);
        SalaDTO dto = new SalaDTO("Sala 4", poltronas);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                    .post("/salas")
                .then()
                    .statusCode(201)
                    .body("id", CoreMatchers.notNullValue())
                    .body("nome",CoreMatchers.is("Sala 4"))
                    .body("listaPoltrona.size()",CoreMatchers.is(2))
                    .body("listaPoltrona[0].nome", CoreMatchers.is("K5"))
                    .body("listaPoltrona[0].estaOcupada", CoreMatchers.is(true))
                    .body("listaPoltrona[1].nome",CoreMatchers.is("L9"))
                    .body("listaPoltrona[1].estaOcupada", CoreMatchers.is(false));   
    }

    @Test
    void atualizarTest() {
        PoltronaDTO p_dto = new PoltronaDTO("U2", true);
        PoltronaDTO polt_dto = new PoltronaDTO("V5", false);
        List<PoltronaDTO> listaDTO = List.of(p_dto,polt_dto);
        SalaDTO salaDTO = new SalaDTO("Sala 8", listaDTO);
        SalaDTOResponse response = salaservice.create(salaDTO);
        PoltronaDTO p_dtoi = new PoltronaDTO("M4", false);
        PoltronaDTO p_dtoy = new PoltronaDTO("X5", true);
        List<PoltronaDTO> listaDT = List.of(p_dtoi,p_dtoy);
        SalaDTO dtoUpdate = new SalaDTO("Sala 43", listaDT);
        RestAssured.given()
                   .contentType(ContentType.JSON)
                   .body(dtoUpdate)
                   .when()
                        .put("/salas/"+response.id())
                    .then()
                        .statusCode(204);
        response = salaservice.findById(response.id());
        assertEquals(dtoUpdate.nome(), response.nome());
        assertEquals(dtoUpdate.poltronas().get(0).nome(), response.listaPoltrona().get(0).nome());
        assertEquals(dtoUpdate.poltronas().get(0).estaOcupada(),response.listaPoltrona().get(0).estaOcupada());
        assertEquals(dtoUpdate.poltronas().get(1).nome(), response.listaPoltrona().get(1).nome());
        assertEquals(dtoUpdate.poltronas().get(1).estaOcupada(), response.listaPoltrona().get(1).estaOcupada());

    }

    @Test
    void apagarTest() {
        PoltronaDTO p = new PoltronaDTO("H5", false);
        PoltronaDTO pp = new PoltronaDTO("M9", true);
        List<PoltronaDTO> lp = List.of(p,pp);
        SalaDTO dto = new SalaDTO("Sala 88", lp);
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
