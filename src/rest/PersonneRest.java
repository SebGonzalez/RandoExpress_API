package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
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

import org.hibernate.exception.ConstraintViolationException;

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
	@Path("personne/mail/{mail}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPersonByMail(@PathParam("mail") String mail) {
    	Personne p = personne.getPersonByMail(mail);
    	if(p != null) {
    		return Response.ok(p).build();
    	}
		
    	return Response.status(404).entity("Person not found").build();
	}
    
    @POST
    @Path("personne")
    @Consumes("application/json")
    public Response addPerson(Personne p) {
    	if(p.getId() != null) p.setId(null);
    	if(personne.getPersonByMail(p.getMail()) != null) {
    		return Response.status(Response.Status.OK)
					.entity("{ \"message\" : \"Le mail existe déjà\" }")
					.type(MediaType.APPLICATION_JSON).build();
    	}
    	System.out.println(p);
    	try {
			personne.save(p);
		} catch (EJBException e) {
			return Response.status(Response.Status.OK)
					.entity("{ \"message\" : \"Mauvais format\" }")
					.type(MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.OK)
				.entity("{ \"message\" : \"Personne ajoutée\", \"id\" : \"" + p.getId() + "\"}")
				.type(MediaType.APPLICATION_JSON).build();
    }
    
    @PUT
    @Path("personne")
    @Consumes("application/json")
    public void editPerson(Personne p) {
    	personne.save(p);
    }
    
    @POST
    @Path("")
    public void changePassword() {
    	
    }
    
    @DELETE
    @Path("personne/{id}")
    public void deletePerson(@PathParam("id") String id) {
    	personne.remove(Long.parseLong(id));
    }
}
