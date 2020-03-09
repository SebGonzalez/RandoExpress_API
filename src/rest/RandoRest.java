package rest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import dao.RandoDAO;
import models.Personne;
import models.Rando;

@Path("/rest")
public class RandoRest {

	@EJB
	RandoDAO randoDAO;
	@EJB
	PersonneDAO personneDAO;

	@GET
	@Path("randos")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response find() {
		List<Rando> list = randoDAO.getAllRandos();
		GenericEntity<List<Rando>> entity = new GenericEntity<List<Rando>>(list) {
		};
		return Response.ok(entity).build();
	}

	@GET
	@Path("rando/id/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRandoById(@PathParam("id") String id) {
		Rando p = randoDAO.getRandoById(Long.parseLong(id));
		if (p != null) {
			return Response.ok(p).build();
		}

		return Response.status(404).entity("Rando not found").build();
	}
	
	@GET
	@Path("randos/person/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRandoPersonne(@PathParam("id") String id) {
		Personne p = personneDAO.getPersonById(Long.parseLong(id));
		if (p == null) {
			return Response.status(404).entity("Personne not found").build();
		}

		List<Rando> list = randoDAO.getRandosPersonne(p);
		GenericEntity<List<Rando>> entity = new GenericEntity<List<Rando>>(list) {
		};
		return Response.ok(entity).build();
	}
	
	@GET
	@Path("randos/person/old/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOldRandoPersonne(@PathParam("id") String id) {
		Personne p = personneDAO.getPersonById(Long.parseLong(id));
		if (p == null) {
			return Response.status(404).entity("Personne not found").build();
		}

		List<Rando> list = randoDAO.getRandosHistoriquePersonne(p);
		GenericEntity<List<Rando>> entity = new GenericEntity<List<Rando>>(list) {
		};
		return Response.ok(entity).build();
	}

	@POST
	@Path("rando")
	@Consumes("application/json")
	public void addRando(Rando r) {
		if (r.getId() != null)
			r.setId(null);
		System.out.println(r);
		randoDAO.save(r);
	}

	@PUT
	@Path("rando")
	@Consumes("application/json")
	public void editRando(Rando r) {
		randoDAO.save(r);
	}

	@DELETE
	@Path("rando/{id}")
	public void deleteRando(@PathParam("id") String id) {
		randoDAO.remove(Long.parseLong(id));
	}
	
	@POST
	@Path("rando/{id}/inscription/{mail}")
	public Response inscription(@PathParam("id") String id, @PathParam("mail") String mail) {
		Rando r = randoDAO.getRandoById(Long.parseLong(id));
		if (r == null) {
			return Response.status(404).entity("{ \"message\" : \"rando not found\" }")
					.type(MediaType.APPLICATION_JSON).build();
		}
		Personne p = personneDAO.getPersonByMail(mail);
		if (p == null) {
			return Response.status(404).entity("{ \"message\" : \"Personne not found\" }")
					.type(MediaType.APPLICATION_JSON).build();
		}
		
		randoDAO.inscriptionRando(r, p);
		return Response.status(Response.Status.OK)
				.entity("{ \"message\" : \"Inscription effectuée \"}")
				.type(MediaType.APPLICATION_JSON).build();

	}
	
	@POST
	@Path("rando/{id}/desinscription/{mail}")
	public Response desinscription(@PathParam("id") String id, @PathParam("mail") String mail) {
		Rando r = randoDAO.getRandoById(Long.parseLong(id));
		if (r == null) {
			return Response.status(404).entity("{ \"message\" : \"rando not found\" }")
					.type(MediaType.APPLICATION_JSON).build();
		}
		Personne p = personneDAO.getPersonByMail(mail);
		if (p == null) {
			return Response.status(404).entity("{ \"message\" : \"Personne not found\" }")
					.type(MediaType.APPLICATION_JSON).build();
		}
		
		randoDAO.desinscriptionRando(r, p);
		return Response.status(Response.Status.OK)
				.entity("{ \"message\" : \"Désinscription effectuée \"}")
				.type(MediaType.APPLICATION_JSON).build();

	}
}