package ar.edu.unq.desapp.groupj.backend.services;

import ar.edu.unq.desapp.groupj.backend.model.*;
import ar.edu.unq.desapp.groupj.backend.repositories.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
                "Compraste en ViandasYa!", "*** Detalles en construccion ***" );

        return (existingOrder==null?newOrder:existingOrder);
    }
}
