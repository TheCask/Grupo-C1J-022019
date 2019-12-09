package ar.edu.unq.desapp.groupj.backend.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ar.edu.unq.desapp.groupj.backend.auth.UserAuthenticationNotRequired;
import ar.edu.unq.desapp.groupj.backend.auth.UserAuthenticationRequired;
import ar.edu.unq.desapp.groupj.backend.model.User;
import ar.edu.unq.desapp.groupj.backend.services.UserService;


@Path("/users")
public class UserRest extends BaseRest {
	
	private UserService userService;
	
	public void setUserService(final UserService userService) {
		this.userService = userService;
	}

	@GET
	@Path("/getById/{id}")
	@Produces("application/json")
	@UserAuthenticationNotRequired
	public Response getUserById(@PathParam("id") final Integer id) {
		User user = userService.findById(id);
		if (user==null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(user).build();
	}

	@GET
	@Path("/getBySocialId/{socialId}")
	@Produces("application/json")
	@UserAuthenticationNotRequired
	public Response getUserBySocialId(@PathParam("socialId") final String socialId) {
		User user = userService.findBySocialId(socialId);
		if (user==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Unregistered user " + socialId).build();
		}
		return Response.ok(user).build();
	}

	@GET
	@Path("{mail}/getId")
	@Produces("application/json")
	@UserAuthenticationNotRequired
	public Response getUserIdByMail(@PathParam("mail") final String mail){
		Integer userId = userService.findUserId(mail);
		if (userId==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Unregistered user " + mail).build();
		}
		return Response.ok(userId).build();
	}

	@GET
	@Path("/getAll")
	@Produces("application/json")
	@UserAuthenticationRequired
	public Response getAllUsers() {
		List<User> users = userService.retriveAll();
		if (users.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(users).build();
	}
	
	@PUT
	@Path("/create")
	@Consumes("application/json")
	@Produces("application/json")
	@UserAuthenticationNotRequired
	public Response createUser(final User user){
		
		try{
			user.setId( (Integer)userService.save(user) );
		}
		catch(Exception exception){
			return Response.status(Response.Status.NOT_FOUND).entity("Unable to create user: " + exception.getMessage() ).build();
		}
		
		return Response.ok(user).build();
	}

	@PUT
	@Path("/update")
	@Consumes("application/json")
	@Produces("application/json")
	@UserAuthenticationRequired
	public Response updateUser(final User user){
		try{
			userService.update(user);
		}
		catch(Exception exception){
			return Response.status(Response.Status.NOT_FOUND).entity("Unable to update user: " + exception.getMessage() ).build();
		}
		
		return Response.ok(user).build();
	}
	
	@DELETE
	@Path("/removeById/{id}")
	@Produces("application/json")
	@UserAuthenticationRequired
	public Response removeUser(@PathParam("id") final Integer id){
		try{
			userService.deleteById(id);
		}
		catch(Exception exception){
			return Response.status(Response.Status.NOT_FOUND).entity("The user with id: " + id + ", doesn't exist in our database").build();
		}
		return Response.ok().build();
	}
}
