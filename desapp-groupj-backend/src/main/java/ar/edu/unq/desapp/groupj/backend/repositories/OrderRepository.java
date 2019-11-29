package ar.edu.unq.desapp.groupj.backend.repositories;

import ar.edu.unq.desapp.groupj.backend.model.Order;
import ar.edu.unq.desapp.groupj.backend.model.OrderDetail;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class OrderRepository extends HibernateGenericDAO<Order> implements GenericRepository<Order> {

    private static final long serialVersionUID = -8729996946214099004L;

    @Override
    protected Class<Order> getDomainClass() {
        return Order.class;
    }

    @Override
    public Serializable save(Order order) {
        Serializable orderId;
        Order existingOrder = getOrderByMenuIdAndDeliveryDate(order.getMenu().getId(), order.getDeliveryDate() );

        if( existingOrder != null ) {
            orderId = existingOrder.getId();
            order.setId(existingOrder.getId());
            super.update(order);
        }
        else {
            orderId = super.save(order);
        }

        return orderId;
    }

    public Order getOrderByMenuIdAndDeliveryDate(Integer menuId, LocalDate deliveryDate ) {
        List<Order> existingOrders = (List<Order>) this.getHibernateTemplate()
                .find(" FROM Order o WHERE menu_id = " + menuId.toString() + " and deliveryDate = '" + deliveryDate.toString() + "'");

        if( existingOrders.size() > 0 )
            return existingOrders.get(0);

        return null;
    }

    public List<OrderDetail> getOrderDetailsByUserId(Integer userId) {
        return (List<OrderDetail>) this.getHibernateTemplate()
                .find(" FROM OrderDetail o WHERE user_id = " + userId.toString() );
    }

    public Serializable createOrderDetail( OrderDetail detail ) {
        Serializable id;

        id = this.getHibernateTemplate().save(detail);
        this.getHibernateTemplate().flush();

        return id;
    }

}