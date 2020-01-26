package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.PersonneDAO;
import models.Personne;

@Path("/rest")
public class PersonneRest {

	@EJB
	PersonneDAO personne;
    
    @GET
	@Path("personnes")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllPersons() {
		try {
			GenericEntity<List<Personne>> genericEntity = new GenericEntity<List<Personne>>(personne.getAllPersons()){};
			return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
    
    @GET
	@Path("personne/id/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPersonById(@PathParam("id") String id) {
    	Personne p = personne.getPersonById(Long.parseLong(id));
    	if(p != null) {
    		return Response.ok(p).build();
    	}
		
    	return Response.status(404).entity("Person not found").build();
	}
    
    @GET
	@Path("personne/uuid/{uuid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPersonByUuid(@PathParam("uuid") String uuid) {
    	Personne p = personne.getPersonByUuid(uuid);
    	if(p != null) {
    		return Response.ok(p).build();
    	}
		
    	return Response.status(404).entity("Person not found").build();
	}
    
    @POST
    @Path("personne")
    @Consumes("application/json")
    public void addPerson(Personne p) {
    	if(p.getId() != null) p.setId(null);
    	System.out.println(p);
    	personne.save(p);
    }
    
    @PUT
    @Path("personne")
    @Consumes("application/json")
    public void editPerson(Personne p) {
    	personne.save(p);
    }
    
    @DELETE
    @Path("personne/{id}")
    public void deletePerson(@PathParam("id") String id) {
    	personne.remove(Long.parseLong(id));
    }
}
