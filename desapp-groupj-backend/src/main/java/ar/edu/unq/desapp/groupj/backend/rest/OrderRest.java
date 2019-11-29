package ar.edu.unq.desapp.groupj.backend.rest;

import ar.edu.unq.desapp.groupj.backend.auth.UserAuthenticationRequired;
import ar.edu.unq.desapp.groupj.backend.model.Order;
import ar.edu.unq.desapp.groupj.backend.model.OrderDetail;
import ar.edu.unq.desapp.groupj.backend.services.OrderService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/orders")
public class OrderRest extends BaseRest {

    private OrderService orderService;

    public void setOrderService( OrderService orderService ) { this.orderService = orderService; }

    @PUT
    @Path("/create")
    @Consumes("application/json")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response createOrder(final Order order){
        try{
            order.setId( (Integer)orderService.save(order) );
        }
        catch(Exception exception){
            return Response.status(Response.Status.NOT_FOUND).entity("Unable to create order: " + exception.getMessage() ).build();
        }
        return Response.ok(order).build();
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response updateOrder(final Order order){
        try{
            orderService.update(order);
        }
        catch(Exception exception){
            return Response.status(Response.Status.NOT_FOUND).entity("Unable to update order: " + exception.getMessage() ).build();
        }
        return Response.ok(order).build();
    }

    @GET
    @Path("/details/getByUserId/{id}")
    @Produces("application/json")
    public Response getOrderDetailsByUserId(@PathParam("id") final Integer id) { //returns user's menus list
        List<OrderDetail> orderDetails = orderService.getOrderDetailsByUserId(id);
        if (orderDetails==null || orderDetails.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(orderDetails).build();
    }

    @GET
    @Path("/getById/{id}")
    @Produces("application/json")
    public Response getOrderById(@PathParam("id") final Integer id) {
        Order order = orderService.findById(id);
        if (order==null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(order).build();
    }

    @GET
    @Path("/getByMenuIdAndDeliveryDate/{menuId}/{deliveryDate}")
    @Produces("application/json")
    public Response getByMenuIdAndDeliveryDate(@PathParam("menuId") final Integer menuId,
                                               @PathParam("deliveryDate") final String deliveryDate) {
        LocalDate _deliveryDate = LocalDate.parse(deliveryDate);
        Order order = orderService.getOrderByMenuIdAndDeliveryDate(menuId,_deliveryDate);
        if (order==null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(order).build();
    }

}
