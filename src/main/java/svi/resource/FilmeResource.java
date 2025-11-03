package svi.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import svi.dto.FilmeDTO;
import svi.model.Filme;
import svi.service.FilmeService;

@Path("/filmes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FilmeResource {

    @Inject
    FilmeService service;

    @GET
    public List<Filme> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Filme buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/buscar")
    public List<Filme> buscarPorTitulo(@QueryParam("titulo") String titulo) {
        return service.findByTitulo(titulo);
    }

    @GET
    @Path("/classificacao")
    public List<Filme> buscarPorClassificacao(@QueryParam("valor") String classificacao) {
        return service.findByClassificacaoIndicativa(classificacao);
    }

    @POST
    @Transactional
    public Filme criar(FilmeDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, FilmeDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        service.delete(id);
    }
}

