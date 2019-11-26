package ar.edu.unq.desapp.groupj.backend.repositories;

import ar.edu.unq.desapp.groupj.backend.model.Order;
import ar.edu.unq.desapp.groupj.backend.model.OrderDetail;

import java.io.Serializable;
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
        List<Order> existingOrders = (List<Order>) this.getHibernateTemplate()
                .find(" FROM orders o WHERE menu_id = " + order.getMenu().getId().toString() + " and deliveryDate = '" + order.getDeliveryDate().toString() + "'");

        if( existingOrders.size() > 0 ) {
            orderId = existingOrders.get(0).getId();
            order.setId((Integer)orderId);
            super.update(order);
        }
        else
            orderId = super.save(order);

        return orderId;
    }

    public List<OrderDetail> getOrderDetailsByUserId(Integer userId) {
        return (List<OrderDetail>) this.getHibernateTemplate()
                .find(" FROM orders_details o WHERE user_id = " + userId.toString() );
    }

}