package svi.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import svi.service.GeneroService;
import jakarta.ws.rs.core.MediaType;

@Path("/generos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GeneroResource {
    @Inject
    GeneroService service;
    
    @GET
    public String getGeneros() {
       return service.getGeneros();
        
    } 

}
