package ar.edu.unq.desapp.groupj.backend.rest;

import ar.edu.unq.desapp.groupj.backend.auth.UserAuthenticationRequired;
import ar.edu.unq.desapp.groupj.backend.model.FoodService;
import ar.edu.unq.desapp.groupj.backend.services.FoodServiceService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/foodServices")
public class FoodServiceRest extends BaseRest {

    private FoodServiceService foodServiceService;

    public void setFoodServiceService( FoodServiceService service ) { this.foodServiceService = service; }

    @GET
    @Path("/getById/{id}")
    @Produces("application/json")
    public Response getFoodServiceById(@PathParam("id") final Integer id) {
        FoodService foodService = foodServiceService.findById(id);
        if (foodService==null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(foodService).build();
    }

    @GET
    @Path("/getByUserId/{id}")
    @Produces("application/json")
    public Response getFoodServicesByUserId(@PathParam("id") final Integer id) {
        List<FoodService> foodServices = foodServiceService.findByUserId(id);
        if (foodServices.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(foodServices).build();
    }

    @GET
    @Path("/getAll")
    @Produces("application/json")
    public Response getAllFoodServices() {
        List<FoodService> foodServices = foodServiceService.retriveAll();
        if (foodServices.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(foodServices).build();
    }

    @PUT
    @Path("/create")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createFoodService(final FoodService foodService){

        try{
            foodService.setId( (Integer)foodServiceService.save(foodService) );
        }
        catch(Exception exception){
            return Response.status(Response.Status.NOT_FOUND).entity("Unable to create food service: " + exception.getMessage() ).build();
        }

        return Response.ok(foodService).build();
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response updateFoodService(final FoodService foodService){
        try{
            foodServiceService.update(foodService);
        }
        catch(Exception exception){
            return Response.status(Response.Status.NOT_FOUND).entity("Unable to update food service: " + exception.getMessage() ).build();
        }

        return Response.ok(foodService).build();
    }

    @DELETE
    @Path("/removeById/{id}")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response removeFoodService(@PathParam("id") final Integer id){
        try{
            foodServiceService.deleteById(id);
        }
        catch(Exception exception){
            return Response.status(Response.Status.NOT_FOUND).entity("The food service with id: " + id + ", doesn't exist in our database").build();
        }
        return Response.ok().build();
    }
}
