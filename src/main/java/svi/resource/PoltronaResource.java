package svi.resource;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import svi.dto.PoltronaDTO;
import svi.dto.PoltronaDTOResponse;

import svi.service.PoltronaService;

@Path("/poltronas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PoltronaResource {

    @Inject
    PoltronaService service;

    @GET
    public List<PoltronaDTOResponse> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public PoltronaDTOResponse buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/buscar")
    public List<PoltronaDTOResponse> buscarPorNome(@QueryParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @GET
    @Path("/disponibilidade")
    public List<PoltronaDTOResponse> buscarPorDisponibilidade(@QueryParam("estaOcupada") Boolean estaOcupada) {
        return service.findByDisponibilidade(estaOcupada);
    }

    @POST
    @Transactional
    public Response criar(PoltronaDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, PoltronaDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        service.delete(id);
    }
}
