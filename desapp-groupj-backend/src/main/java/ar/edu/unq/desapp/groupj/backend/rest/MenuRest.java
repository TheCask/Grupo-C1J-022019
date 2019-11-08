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

import ar.edu.unq.desapp.groupj.backend.auth.UserAuthenticationRequired;
import ar.edu.unq.desapp.groupj.backend.model.User;
import ar.edu.unq.desapp.groupj.backend.model.Menu;
import ar.edu.unq.desapp.groupj.backend.rest.BaseRest;
import ar.edu.unq.desapp.groupj.backend.services.MenuService;
import ar.edu.unq.desapp.groupj.backend.services.UserService;

@Path("/menus")
public class MenuRest extends BaseRest {

    private MenuService menuService;
    private UserService userService;

    public void setMenuService(final MenuService menuService) { this.menuService = menuService; }
    public void setUserService(final UserService userService) { this.userService = userService; }

    @GET
    @Path("/getById/{id}")
    @Produces("application/json")
    public Response getMenuById(@PathParam("id") final Integer id) {
        Menu menu = menuService.findById(id);
        if (menu==null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(menu).build();
    }

    @PUT
    @Path("/create")
    @Consumes("application/json")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response createMenu(final Menu menu){

        try{
            menu.setId( (Integer)menuService.save(menu) );
        }
        catch(Exception exception){
            return Response.status(Response.Status.NOT_FOUND).entity("Unable to create menu: " + exception.getMessage() ).build();
        }
        return Response.ok(menu).build();
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response updateMenu(final Menu menu){
        try{
            menuService.update(menu);
        }
        catch(Exception exception){
            return Response.status(Response.Status.NOT_FOUND).entity("Unable to update menu: " + exception.getMessage() ).build();
        }
        return Response.ok(menu).build();
    }

    @DELETE
    @Path("/removeById/{id}")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response removeMenu(@PathParam("id") final Integer id){
        try{
            menuService.deleteById(id);
        }
        catch(Exception exception){
            return Response.status(Response.Status.NOT_FOUND).entity("The menu with id: " + id + ", doesn't exist in our database").build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/getAll")
    @Produces("application/json")
    public Response getAllMenus() {
        List<Menu> menus = menuService.retriveAll();
        if (menus.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(menus).build();
    }

    @GET
    @Path("/getByName/{name}")
    @Produces("application/json")
    public Response findMenusByName(@PathParam("name") final String name) {
        List<Menu> menus = menuService.findByName(name);
        if (menus.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(menus).build();
    }

    @GET
    @Path("/getByUserId/{id}")
    @Produces("application/json")
    public Response findMenusByUserId(@PathParam("id") final Integer id) { //returns user's menus list
        User user = userService.findById(id);
        if (user==null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Menu> menus = menuService.findByUser(user);
        if (menus.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(menus).build();
    }
}
