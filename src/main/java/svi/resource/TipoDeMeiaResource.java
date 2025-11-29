package svi.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import svi.service.TipoDeMeiaService;
import jakarta.ws.rs.core.MediaType;

@Path("/tiposdemeia")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoDeMeiaResource {
    @Inject
    TipoDeMeiaService service;

    @GET
    public String getTiposDeMeia() {
        return service.getTiposMeia();
    }
}
