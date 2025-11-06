package svi.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import svi.dto.AtorDTO;
import svi.dto.AtorDTOResponse;

import svi.service.AtorService;
@Path("/atores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AtorResource {
    
    @Inject
    AtorService service;

    @GET
    public List<AtorDTOResponse> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public AtorDTOResponse buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/buscar")
    public List<AtorDTOResponse> buscarPorNome(@QueryParam("nome") String nome) {
        return service.findByNome(nome);
    }

   @POST 
   public Response incluir(AtorDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build(); }

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
