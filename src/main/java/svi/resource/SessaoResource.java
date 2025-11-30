package svi.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import svi.dto.SessaoDTO;
import svi.dto.SessaoDTOResponse;

import svi.service.SessaoService;

@Path("/sessoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SessaoResource {

    @Inject
    SessaoService service;

    @GET
    public List<SessaoDTOResponse> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public SessaoDTOResponse buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Transactional
    public SessaoDTOResponse criar(SessaoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, SessaoDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        service.delete(id);
    }
}
