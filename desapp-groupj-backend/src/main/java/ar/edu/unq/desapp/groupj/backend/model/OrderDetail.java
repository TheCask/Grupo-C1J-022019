package ar.edu.unq.desapp.groupj.backend.model;

import ar.edu.unq.desapp.groupj.backend.json.LocalTimeDeserializer;
import ar.edu.unq.desapp.groupj.backend.json.LocalTimeSerializer;
import ar.edu.unq.desapp.groupj.backend.model.exception.OrderDetailException;
import ar.edu.unq.desapp.groupj.backend.repositories.converters.LocalTimeAttributeConverter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="order_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Convert(converter = LocalTimeAttributeConverter.class )
    private LocalTime deliveryTime;

    private DeliveryType deliveryType;

    private Integer requestedAmount;

    private OrderStatus status = OrderStatus.Pending;

    private OrderDetail() {}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //@JsonBackReference
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonSerialize(using = LocalTimeSerializer.class)
    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    public void setDeliveryTime(LocalTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(Integer requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public void confirmOrderToUser(LocalDate deliveryDate, double creditToReturn) {
        if (creditToReturn !=0) {
            double credit = creditToReturn * this.getRequestedAmount();
            this.getUser().chargeCredit(credit);
            this.getOrder().getProvider().withdrawCredit(credit);
        }
        // TODO notify provider and client
    }

    public OrderStatus getStatus() { return this.status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public void cancel() {
        changeStatus( OrderStatus.Pending, OrderStatus.Cancelled );
    }

    public void confirm() {
        changeStatus( OrderStatus.Pending, OrderStatus.Confirmed );
    }

    public void confirmReception() {
        changeStatus( OrderStatus.Confirmed, OrderStatus.Delivered );
    }

    public Double getTotalCost() {
        return getOrder().getMenu().computeTotalCost(getRequestedAmount(),getDeliveryType());
    }

    public Double computeRefund() {
        Order order = getOrder();
        Menu menu = order.getMenu();
        return menu.computeTotalCost(getRequestedAmount(),getDeliveryType()) -
                ((getRequestedAmount() * menu.computePriceForQuantity(order.getRequestedAmount())) + menu.computeDeliveryCost(getDeliveryType()));
    }


    private void changeStatus( OrderStatus from, OrderStatus to ) {
        if( this.getStatus() == from ) {
            this.setStatus(to);
        }
        else {
            throw new OrderDetailException("Order can't be changed to status '" + to.toString() + "' as it is not currently '" + from.toString() + "'. Now it is '" + this.getStatus().toString() + "'." );
        }
    }


    public static class Builder {
        private Order order;
        private User user;
        private LocalTime deliveryTime;
        private DeliveryType deliveryType;
        private Integer requestedAmount;
        private OrderStatus status;

        private Builder() {}

        public static Builder anOrderDetail() {
            return new Builder();
        }

        public OrderDetail build() {
            OrderDetail detail = new OrderDetail();

            detail.setOrder(this.order);
            detail.setUser(this.user);
            detail.setDeliveryTime(this.deliveryTime);
            detail.setDeliveryType(this.deliveryType);
            detail.setRequestedAmount(this.requestedAmount);
            detail.setStatus(this.status);

            return detail;
        }

        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withDeliveryTime(LocalTime deliveryTime) {
            this.deliveryTime = deliveryTime;
            return this;
        }

        public Builder withDeliveryType(DeliveryType deliveryType) {
            this.deliveryType = deliveryType;
            return this;
        }

        public Builder withRequestedAmount(Integer requestedAmount) {
            this.requestedAmount = requestedAmount;
            return this;
        }

        public Builder withStatus(OrderStatus status) {
            this.status = status;
            return this;
        }
    }

}
