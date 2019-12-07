package ar.edu.unq.desapp.groupj.backend.rest;

import ar.edu.unq.desapp.groupj.backend.auth.UserAuthenticationRequired;
import ar.edu.unq.desapp.groupj.backend.model.DeliveryType;
import ar.edu.unq.desapp.groupj.backend.model.Order;
import ar.edu.unq.desapp.groupj.backend.model.OrderDetail;
import ar.edu.unq.desapp.groupj.backend.services.FoodServiceService;
import ar.edu.unq.desapp.groupj.backend.services.MenuService;
import ar.edu.unq.desapp.groupj.backend.services.OrderService;
import ar.edu.unq.desapp.groupj.backend.services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @Path("/clientPlaceOrder/{clientId}/{menuId}/{deliveryDate}/{deliveryTime}/{deliveryType}/{requestedAmount}")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response clientPlaceOrder( @PathParam("clientId") final Integer clientId,
                                      @PathParam("menuId") final Integer menuId,
                                      @PathParam("deliveryDate") final String sDeliveryDate,
                                      @PathParam("deliveryTime") final String sDeliveryTime,
                                      @PathParam("deliveryType") final DeliveryType deliveryType,
                                      @PathParam("requestedAmount") final Integer requestedAmount ){
        Order newOrder;

        try{
            LocalDate deliveryDate = LocalDate.parse(sDeliveryDate);
            LocalTime deliveryTime = LocalTime.parse(sDeliveryTime);
            newOrder = this.orderService.clientPlaceOrder( clientId, menuId, deliveryDate, deliveryTime, deliveryType, requestedAmount );
        }
        catch(Exception exception){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unable to create order: " + exception.getMessage() ).build();
        }
        return Response.ok(newOrder).build();
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

    @PUT
    @Path("/details/cancel/{orderDetailId}")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response cancelOrderDetail(@PathParam("orderDetailId") final Integer orderDetailId){
        OrderDetail cancelledOrder;
        try{
            cancelledOrder = orderService.userCancelOrderDetail(orderDetailId);
        }
        catch(Exception exception){
            return Response.status(Response.Status.NOT_FOUND).entity("Unable to cancel order: " + exception.getMessage() ).build();
        }
        return Response.ok(cancelledOrder).build();
    }

    @PUT
    @Path("/details/confirmReception/{orderDetailId}")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response confirmReception(@PathParam("orderDetailId") final Integer orderDetailId){
        OrderDetail cancelledOrder;
        try{
            cancelledOrder = orderService.userConfirmReceptionOrderDetail(orderDetailId);
        }
        catch(Exception exception){
            return Response.status(Response.Status.NOT_FOUND).entity("Unable to confirm order reception: " + exception.getMessage() ).build();
        }
        return Response.ok(cancelledOrder).build();
    }

    @GET
    @Path("/details/getByUserId/{id}")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response getOrderDetailsByUserId(@PathParam("id") final Integer id) { //returns user's menus list
        List<OrderDetail> orderDetails = orderService.getOrderDetailsByUserId(id);
        if (orderDetails==null || orderDetails.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(orderDetails).build();
    }

    @PUT
    @Path("/details/create")
    @Consumes("application/json")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response createOrderDetail(final OrderDetail orderDetail){
        try{
            orderDetail.setId( (Integer)orderService.createOrderDetail(orderDetail) );
        }
        catch(Exception exception){
            return Response.status(Response.Status.NOT_FOUND).entity("Unable to create order detail: " + exception.getMessage() ).build();
        }
        return Response.ok(orderDetail).build();
    }

    @GET
    @Path("/getById/{id}")
    @Produces("application/json")
    @UserAuthenticationRequired
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
    @UserAuthenticationRequired
    public Response getByMenuIdAndDeliveryDate(@PathParam("menuId") final Integer menuId,
                                               @PathParam("deliveryDate") final String deliveryDate) {
        LocalDate _deliveryDate = LocalDate.parse(deliveryDate);
        Order order = orderService.getOrderByMenuIdAndDeliveryDate(menuId,_deliveryDate);
        if (order==null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(order).build();
    }

    @PUT
    @Path("/runDailyClosure/{daysToOrderClosure}")
    @Produces("application/json")
    @UserAuthenticationRequired
    public Response runDailyClosure(@PathParam("daysToOrderClosure") final Integer daysToOrderClosure){

        try{
            this.orderService.runDailyClosure(daysToOrderClosure);
        }
        catch(Exception exception){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Problemas running daily close: " + exception.getMessage() ).build();
        }

        return Response.ok().build();
    }

}
