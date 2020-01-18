package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.PersonneDAO;
import models.Personne;

@Path("/ws")
public class PersonneRest {

	@EJB
	PersonneDAO personne;
	
    @GET()
    @Path("hello")
    public String hello() {
        return "Hello";
    }
    
    @GET
	@Path("personnes")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response find() {
		try {
			GenericEntity<List<Personne>> genericEntity = new GenericEntity<List<Personne>>(personne.getAllPersons()){};
			return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
