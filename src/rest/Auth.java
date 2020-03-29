package rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import dao.PersonneDAO;
import models.Admin;
import models.Personne;
import models.User;

@Path("/rest")
public class Auth {

	@EJB
	PersonneDAO personneDAO;

	@POST
	@Path("auth")
	@Consumes("application/json")
	public Response auth(User u) {

		System.out.println(u.getMail() + " " + u.getPassword());
		Personne p = personneDAO.getPersonByMail(u.getMail());

		if (p == null) {
			return Response.status(Response.Status.OK)
					.entity("{ \"message\" : \"Erreur de connexion, mail inexistant\" }")
					.type(MediaType.APPLICATION_JSON).build();
		} else if (!p.getPassword().equals(u.getPassword())) {
			return Response.status(Response.Status.OK)
					.entity("{ \"message\" : \"Erreur de connexion, mot de passe incorrect\" }")
					.type(MediaType.APPLICATION_JSON).build();
		} else {
			String token = null;
			try {
				Algorithm algorithm = Algorithm.HMAC256("secret");
				token = JWT.create().withIssuer("auth0").sign(algorithm);
			} catch (JWTCreationException exception) {
				
			}
			
			
			return Response.status(Response.Status.OK)
					.entity("{ \"message\" : \"Connexion validé\",  \"jwt\" : \"" + token + "\", \"name\" :  \"" + p.getName() + "\", \"firstName\" :  \"" + p.getFirstName() + "\", \"mail\" :  \"" + p.getMail() +  "\", \"id\" :  \"" + p.getId() + "\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@POST
	@Path("authAdmin")
	@Consumes("application/json")
	public Response authAdmin(User u) {

		System.out.println(u.getMail() + " " + u.getPassword());
		Admin a = personneDAO.getAdminByMail(u.getMail());

		if (a == null) {
			return Response.status(Response.Status.OK)
					.entity("{ \"message\" : \"Erreur de connexion, mail inexistant\" }")
					.type(MediaType.APPLICATION_JSON).build();
		} else if (!a.getPassword().equals(u.getPassword())) {
			return Response.status(Response.Status.OK)
					.entity("{ \"message\" : \"Erreur de connexion, mot de passe incorrect\" }")
					.type(MediaType.APPLICATION_JSON).build();
		} else {
			String token = null;
			try {
				Algorithm algorithm = Algorithm.HMAC256("secret");
				token = JWT.create().withIssuer("auth0").sign(algorithm);
			} catch (JWTCreationException exception) {
				
			}
			
			System.out.println("Connexion OK");
			
			
			return Response.status(Response.Status.OK)
					.entity("{ \"message\" : \"Connexion validé\",  \"jwt\" : \"" + token + "\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}
	}
}
