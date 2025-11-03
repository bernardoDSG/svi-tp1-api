package svi.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import svi.dto.AtorDTO;
import svi.model.Ator;
import svi.service.AtorService;
@Path("/atores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AtorResource {
    
    @Inject
    AtorService service;

    @GET
    public List<Ator> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Ator buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/buscar")
    public List<Ator> buscarPorNome(@QueryParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @POST
    @Transactional
    public Ator criar(AtorDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, AtorDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        service.delete(id);
    }
}
