package svi.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import svi.dto.PoltronaDTO;
import svi.model.Poltrona;
import svi.service.PoltronaService;

@Path("/poltronas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PoltronaResource {

    @Inject
    PoltronaService service;

    @GET
    public List<Poltrona> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Poltrona buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/buscar")
    public List<Poltrona> buscarPorNome(@QueryParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @GET
    @Path("/disponibilidade")
    public List<Poltrona> buscarPorDisponibilidade(@QueryParam("ocupada") Boolean ocupada) {
        return service.findByDisponibilidade(ocupada);
    }

    @POST
    @Transactional
    public Poltrona criar(PoltronaDTO dto) {
        return service.create(dto);
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
