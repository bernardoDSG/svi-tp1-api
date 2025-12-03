package svi;

import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import svi.converter.ConverterGeneroListString;
import svi.converter.ConverterIdObjeto;
import svi.converter.ConverterPremioString;
import svi.dto.FilmeDTO;
import svi.dto.FilmeDTOResponse;

import svi.service.AtorService;
import svi.service.FilmeService;
import svi.service.IdiomaService;

@QuarkusTest
public class FilmeResourceTest {

    ConverterPremioString converter = new ConverterPremioString();
    ConverterIdObjeto converterid = new ConverterIdObjeto();
    ConverterGeneroListString converterGenero = new ConverterGeneroListString();
    @Inject
    AtorService atorservice;
    @Inject
    FilmeService filmeService;
    @Inject
    IdiomaService idiomaService;
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

    @Test
    public void buscarPorTituloTest() {
        RestAssured.given()
            .contentType(ContentType.JSON)
            .queryParam("titulo", "Embrapa: Uma história de guerra")
            .when()
                .get("/filmes/buscar")
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Embrapa: Uma história de guerra"))
                .body("[0].id", notNullValue());
    }

     @Test
    public void buscarPorClassificacaoIndicativaTest() {
        RestAssured.given()
            .contentType(ContentType.JSON)
            .queryParam("classificacaoIndicativa", "Livre")
            .when()
                .get("/filmes/classificacao")
            .then()
                .statusCode(200)
                .body("[0].classificacaoIndicativa", is("Livre"))
                .body("[0].id", notNullValue());
    }

    @Test
    public void incluirTest() {
        FilmeDTO dtoFilme = new FilmeDTO("Filme: o filme sobre o filme", "4h50min", "o filme que fala sobre o filme.", "12 anos", List.of(1l,2l), List.of(1l,2l), List.of(1l,2l), 1l);

        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(dtoFilme)
            .when()
                .post("/filmes")
            .then()
                .statusCode(201)
                .body("id", CoreMatchers.notNullValue(),
                      "titulo", CoreMatchers.is("Filme: o filme sobre o filme"),
                      "duracao", CoreMatchers.is("4h50min"),
                      "sinopse", CoreMatchers.is("o filme que fala sobre o filme."),
                      "classificacaoIndicativa", CoreMatchers.is("12 anos"));
    }

    @Test
    public void alterarTest() {
        FilmeDTO dtoFilme = new FilmeDTO("Piratas do Vale do Sílicio", "2h", "Steve Jobs e Bill Gates disputam o pioneirismo de mercado no inicio da era dos computadores", "14 anos", List.of(1l,2l),List.of(1l,2l), List.of(1l,2l), 1l);
        FilmeDTOResponse response = filmeService.create(dtoFilme);
        FilmeDTO dtoUpdate = new FilmeDTO("Steve Jobs", "2h10min", "A biografia do co-fundador da Apple.", "14 anos", List.of(2l,3l), List.of(2l,3l), List.of(1l,2l), 2l);
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(dtoUpdate)
            .when()
                .put("/filmes/" + response.id())
            .then()
                .statusCode(204);
        response = filmeService.findById(response.id());

        assertEquals(dtoUpdate.titulo(), response.titulo());
        assertEquals(dtoUpdate.duracao(), response.duracao());
        assertEquals(dtoUpdate.sinopse(), response.sinopse());
        assertEquals(dtoUpdate.classificacaoIndicativa(), response.classificacaoIndicativa());
        assertEquals(converter.convertToDatabaseColumn(dtoUpdate.idPremios().stream().map(i -> converterid.premioFromId(i)).toList()), response.premios());
        assertEquals(converterGenero.convertToDatabaseColumn(dtoUpdate.idGeneros().stream().map(i -> converterid.generoFromId(i)).toList()), response.generos());
        assertEquals(dtoUpdate.idAtores().stream().map(i -> atorservice.findById(i)).toList().get(0).nome(), response.atores().get(0).nome());
        assertEquals(dtoUpdate.idAtores().stream().map(i -> atorservice.findById(i)).toList().get(1).nome(), response.atores().get(1).nome());
        assertEquals(idiomaService.findById(dtoUpdate.idIdiomaOriginal()).nome(), response.idioma_original().nome());
    }

    @Test
    public void apagarTest() {
        FilmeDTO dtoFilme = new FilmeDTO("A Origem", "2h28min", "Dom Cobb é um ladrão hábil, o melhor na perigosa arte de extração: roubar valiosos segredos do fundo do subconsciente durante o estado de sonho, quando a mente está mais vulnerável.", "16 anos", List.of(1l,2l), List.of(1l,2l), List.of(1l,2l), 1l);
        FilmeDTOResponse response = filmeService.create(dtoFilme);

        RestAssured.given()
            .when()
                .delete("/filmes/" + response.id())
            .then()
                .statusCode(204);

        response = filmeService.findById(response.id());

        assertNull(response);
    }

    
}
