package ar.edu.unq.desapp.groupj.backend.services;

import ar.edu.unq.desapp.groupj.backend.model.Order;
import ar.edu.unq.desapp.groupj.backend.model.OrderDetail;
import ar.edu.unq.desapp.groupj.backend.repositories.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class OrderService extends GenericService<Order> {

    private static final long serialVersionUID = -2796236622242535843L;

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
}
