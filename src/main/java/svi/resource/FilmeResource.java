package svi.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import svi.dto.FilmeDTO;
import svi.dto.FilmeDTOResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import svi.service.FilmeService;

@Path("/filmes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FilmeResource {

    @Inject
    FilmeService service;

    @GET
    public List<FilmeDTOResponse> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public FilmeDTOResponse buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/buscar")
    public List<FilmeDTOResponse> buscarPorTitulo(@QueryParam("titulo") String titulo) {
        return service.findByTitulo(titulo);
    }

    @GET
    @Path("/classificacao")
    public List<FilmeDTOResponse> buscarPorClassificacao(@QueryParam("classificacaoIndicativa") String classificacao) {
        return service.findByClassificacaoIndicativa(classificacao);
    }

    @POST
    @Transactional
    public Response criar(FilmeDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
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

