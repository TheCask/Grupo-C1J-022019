package ar.edu.unq.desapp.groupj.backend.rest;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/")
public abstract class BaseRest {
	
	// Handle options requests (typically pre-flight requests) for all ****Rest classes...
	@OPTIONS
	@Path("{var:.+}" )
	@Produces("application/json")
	public Response options() {
		return Response.ok().build();
	}	
		
}
