package svi.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import svi.service.PremioService;
import jakarta.ws.rs.core.MediaType;

@Path("/premios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PremioResource {
    @Inject
    PremioService service;


    @GET
    public String getPremios() {
       return service.getPremios();
        
    } 
}
