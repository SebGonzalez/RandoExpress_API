package filters;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Provider
@PreMatching
@WithMyFilter
public class JWTFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext ct) throws IOException {

		System.out.println(ct.getUriInfo().getPath());
		if(ct.getUriInfo().getPath().contains("auth")) return;
		if(ct.getUriInfo().getPath().contains("personne") && ct.getMethod().equals(HttpMethod.POST)) return;
		
		boolean ok = false;
		Map<String, List<String>> headrs = ct.getHeaders();

		// String jwt = headrs.get("Authorization");
		System.out.printf("-- PreMatching \n");
		for (Map.Entry<String, List<String>> entry : headrs.entrySet()) {
			
			if (entry.getKey().equals("Authorization")) {
				ok = true;

				String token = entry.getValue().get(0);
				System.out.println("TOOOOKEN " + token);
				try {
					Algorithm algorithm = Algorithm.HMAC256("secret");
					JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build(); // Reusable verifier
																								// instance
					DecodedJWT jwt = verifier.verify(token);
				} catch (JWTVerificationException exception) {
					System.out.println("BAD request : " + exception.getMessage());
					ct.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("{ \"message\" : \"Token invalide\" }")
							.type(MediaType.APPLICATION_JSON).build());
				}
				break;
			}
		}
		if(!ok)
			ct.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("{ \"message\" : \"Token inexistant\" }")
					.type(MediaType.APPLICATION_JSON).build());

	}

}
