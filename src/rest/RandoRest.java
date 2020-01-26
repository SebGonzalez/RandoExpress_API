package rest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.RandoDAO;
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
    	System.out.println("OOOOOOOO");
    	System.out.println(list.get(0));
    	GenericEntity< List< Rando > > entity = new GenericEntity< List< Rando > >( list ) {};
		return Response.ok( entity ).build();
	}
}