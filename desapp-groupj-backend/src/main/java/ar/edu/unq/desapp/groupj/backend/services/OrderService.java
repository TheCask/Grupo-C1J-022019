package ar.edu.unq.desapp.groupj.backend.services;

import ar.edu.unq.desapp.groupj.backend.model.*;
import ar.edu.unq.desapp.groupj.backend.repositories.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderService extends GenericService<Order> {

    private static final long serialVersionUID = -2796236622242535843L;

    private UserService userService;
    private FoodServiceService foodServiceService;
    private MenuService menuService;
    private EmailSenderService emailSenderService;

    public void setUserService( UserService service ) { this.userService = service; }
    public void setFoodServiceService( FoodServiceService service ) { this.foodServiceService = service; }
    public void setMenuService( MenuService service ) { this.menuService = service; }
    public void setEmailSenderService( EmailSenderService service ) { this.emailSenderService = service; }

    @Transactional
    public List<OrderDetail> getOrderDetailsByUserId(Integer userId) {
        return ((OrderRepository)this.getRepository()).getOrderDetailsByUserId(userId);
    }

    @Transactional
    public Order getOrderByMenuIdAndDeliveryDate(Integer menuId, LocalDate deliveryDate ) {
        return ((OrderRepository)this.getRepository()).getOrderByMenuIdAndDeliveryDate(menuId,deliveryDate);
    }

    @Transactional
    public Serializable createOrderDetail(final OrderDetail detail) {
        return ((OrderRepository)this.getRepository()).createOrderDetail(detail);
    }

    @Transactional
    public Order clientPlaceOrder( Integer clientId, Integer menuId,
                                  LocalDate deliveryDate, LocalTime deliveryTime,
                                  DeliveryType deliveryType, Integer requestedAmount ) {
        User client = this.userService.findById(clientId);
        Menu menu = this.menuService.findById(menuId);
        User provider = menu.getProvider();
        Order existingOrder = this.getOrderByMenuIdAndDeliveryDate( menuId, deliveryDate );
        Order newOrder = provider.placeClientOrder( client, menu.getFoodService(),
                                                menu, deliveryDate, deliveryTime,
                                                deliveryType, requestedAmount );
        String purchaseDescription = composePurchaseEmailText(newOrder.getDetails().iterator().next());

        this.userService.save(client);
        this.userService.save(provider);

        if( existingOrder == null )
            newOrder.setId( (Integer)this.save(newOrder) );
        else {
            newOrder.getDetails().forEach( detail -> {
                detail.setOrder( existingOrder );
                detail.setId((Integer) this.createOrderDetail(detail));
            });
            existingOrder.getDetails().addAll( newOrder.getDetails() );
        }

        this.emailSenderService.backgroundSend( client.getMail(), provider.getMail(),
                "Compraste en ViandasYa!",  purchaseDescription );

        return (existingOrder==null?newOrder:existingOrder);
    }

    private String composePurchaseEmailText(OrderDetail orderDetail) {
        Menu menu = orderDetail.getOrder().getMenu();
        User provider = menu.getProvider();
        String description = "Hola " + orderDetail.getUser().getFirstName() + "!!\n\n" +
                "Compraste " + orderDetail.getRequestedAmount() + " unidad(es) del menu '" + menu.getName() + "'" +
                " ofrecido por el vendedor " + provider.getFirstName() + " " + provider.getLastName() + " (en copia de este mail).\n" +
                "Tu pedido estará listo el " + orderDetail.getOrder().getDeliveryDate().format(DateTimeFormatter.ISO_DATE) + " a las " + orderDetail.getDeliveryTime().format(DateTimeFormatter.ISO_LOCAL_TIME) + ".\n" +
                "El costo de la comida comprada es $" + menu.computePriceForQuantity(orderDetail.getRequestedAmount()) * orderDetail.getRequestedAmount() + ".\n" +
                (orderDetail.getDeliveryType()==DeliveryType.DeliverToAddress? "El costo de envio es $"+menu.computeDeliveryCost(orderDetail.getDeliveryType()) + ".\n" :
                        "Elegiste retirarlo vos, asi que no hay costos de envio.\n") +
                "El costo total de tu compra es $" + menu.computeTotalCost(orderDetail.getRequestedAmount(),orderDetail.getDeliveryType()) + ".\n" +
                "\n\nGracias por tu compra!!\n\n\nViandasYa!";
        return description;
    }

}
