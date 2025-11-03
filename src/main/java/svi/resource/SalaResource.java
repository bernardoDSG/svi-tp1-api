package svi.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import svi.dto.SalaDTO;
import svi.model.Sala;
import svi.service.SalaService;

@Path("/salas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SalaResource {

    @Inject
    SalaService service;

    @GET
    public List<Sala> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Sala buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/buscar")
    public List<Sala> buscarPorNome(@QueryParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @POST
    @Transactional
    public Sala criar(SalaDTO dto) {
        return service.create(dto);
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
