package svi.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import svi.dto.IdiomaDTO;
import svi.dto.IdiomaDTOResponse;

import svi.service.IdiomaService;

@Path("/idiomas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IdiomaResource {

    @Inject
    IdiomaService service;

    @GET
    public List<IdiomaDTOResponse> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public IdiomaDTOResponse buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/buscar")
    public List<IdiomaDTOResponse> buscarPorNome(@QueryParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @GET
    @Path("/sigla")
    public List<IdiomaDTOResponse> buscarPorSigla(@QueryParam("sigla") String sigla) {
        return service.findBySigla(sigla);
    }

    @POST
    @Transactional
    public Response criar(IdiomaDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, IdiomaDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        service.delete(id);
    }
}
