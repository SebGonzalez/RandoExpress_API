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

import dao.RandoDAO;
import models.Personne;
import models.Rando;

@Path("/rest")
public class RandoRest {

	@EJB
	RandoDAO randoDAO;

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
}