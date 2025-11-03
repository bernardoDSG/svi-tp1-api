package svi.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import svi.dto.IdiomaDTO;
import svi.model.Idioma;
import svi.service.IdiomaService;

@Path("/idiomas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IdiomaResource {

    @Inject
    IdiomaService service;

    @GET
    public List<Idioma> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Idioma buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/buscar")
    public List<Idioma> buscarPorNome(@QueryParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @GET
    @Path("/sigla")
    public List<Idioma> buscarPorSigla(@QueryParam("valor") String sigla) {
        return service.findBySigla(sigla);
    }

    @POST
    @Transactional
    public Idioma criar(IdiomaDTO dto) {
        return service.create(dto);
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
