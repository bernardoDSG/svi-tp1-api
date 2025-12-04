package svi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import svi.dto.SessaoDTO;
import svi.dto.SessaoDTOResponse;
import svi.service.SessaoService;

@QuarkusTest
public class SessaoResourceTest {

    @Inject
    SessaoService sessao_service;

    @Test
    public void buscarTodosTest() {
        RestAssured.given()
            .when()
                .get("/sessoes")
            .then()
                .statusCode(200);
    }

    @Test
    public void buscarPorIdTest() {
        RestAssured.given()
            .when()
                .get("/sessoes/1")
            .then()
                .statusCode(200);
    }

    @Test
    public void incluirTest() {
        LocalDateTime horario_inicio = LocalDateTime.of(2025, 04, 12, 12, 30);
        LocalDateTime horario_fim = LocalDateTime.of(2025, 04, 12, 15, 0); 
        SessaoDTO sessao_dto = new SessaoDTO(horario_inicio, horario_fim, 1l, List.of(1l,2l), 1l);
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(sessao_dto)
            .when()
                .post("/sessoes")
            .then()
                .statusCode(201)
                .body("id", CoreMatchers.notNullValue(),
                       "horarioInicio", CoreMatchers.is("2025-04-12T12:30:00"),
                      "horarioFim", CoreMatchers.is("2025-04-12T15:00:00"));
        
    }

    @Test
    public void alterarTest() {
        LocalDateTime horario_inicio = LocalDateTime.of(2025, 06, 18, 10, 0);
        LocalDateTime horario_fim = LocalDateTime.of(2025, 06, 18, 12, 30); 
        SessaoDTO sessao_dto = new SessaoDTO(horario_inicio, horario_fim, 1l, List.of(1l,2l), 1l);
        SessaoDTOResponse sessao_response = sessao_service.create(sessao_dto);
        LocalDateTime horario_i = LocalDateTime.of(2025, 01, 24, 13, 10);
        LocalDateTime horario_f = LocalDateTime.of(2025, 01, 24, 15, 40); 
        SessaoDTO sessao_dto_update = new SessaoDTO(horario_i, horario_f, 2l,List.of(2l,3l) , 2l);
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(sessao_dto_update)
            .when()
                .put("/sessoes/" + sessao_response.id())
            .then()
                .statusCode(204);    
        
        sessao_response = sessao_service.findById(sessao_response.id());
        assertEquals(sessao_dto_update.horarioInicio(), sessao_response.horarioInicio());
        assertEquals(sessao_dto_update.horarioFim(), sessao_response.horarioFim());
        assertEquals(sessao_dto_update.idFilme(), sessao_response.filme().id());
        assertEquals(sessao_dto_update.idsSalas().get(0), sessao_response.salas().get(0).id());
        assertEquals(sessao_dto_update.idsSalas().get(1), sessao_response.salas().get(1).id());
        assertEquals(sessao_dto_update.idIdioma(), sessao_response.idioma().id());
    }

    @Test
    public void apagarTest() {
        LocalDateTime horario_inicio = LocalDateTime.of(2025, 8, 5, 14, 0);
        LocalDateTime horario_fim = LocalDateTime.of(2025, 8, 5, 16, 30);
        SessaoDTO sessao_dto = new SessaoDTO(horario_inicio, horario_fim, 2l, List.of(1l,2l), 1l);
        SessaoDTOResponse sessao_response = sessao_service.create(sessao_dto);
        RestAssured.given()
            .when()
                .delete("/sessoes/" + sessao_response.id())
            .then()
                .statusCode(204);
        sessao_response = sessao_service.findById(sessao_response.id());
        assertNull(sessao_response);
    }
}
