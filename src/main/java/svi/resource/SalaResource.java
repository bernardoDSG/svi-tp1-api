package svi.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import svi.dto.SalaDTO;
import svi.dto.SalaDTOResponse;

import svi.service.SalaService;

@Path("/salas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SalaResource {

    @Inject
    SalaService service;

    @GET
    public List<SalaDTOResponse> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public SalaDTOResponse buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/buscar")
    public List<SalaDTOResponse> buscarPorNome(@QueryParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @POST
    @Transactional
    public Response criar(SalaDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, SalaDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        service.delete(id);
    }
}
