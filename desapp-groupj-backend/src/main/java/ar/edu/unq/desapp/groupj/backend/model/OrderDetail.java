package ar.edu.unq.desapp.groupj.backend.model;

import ar.edu.unq.desapp.groupj.backend.json.LocalTimeDeserializer;
import ar.edu.unq.desapp.groupj.backend.json.LocalTimeSerializer;
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
    private int requestedAmount;

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

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public void confirmOrderToUser(LocalDate deliveryDate, double creditToReturn) {
        if (creditToReturn !=0) {
            double credit = creditToReturn * this.getRequestedAmount();
            this.getUser().chargeCredit((int)credit);
            this.getOrder().getProvider().withdrawCredit((int)credit);
        }
        // TODO notify provider and client
    }


    public static class Builder {
        private Order order;
        private User user;
        private LocalTime deliveryTime;
        private DeliveryType deliveryType;
        private int requestedAmount;

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

        public Builder withRequestedAmount(int requestedAmount) {
            this.requestedAmount = requestedAmount;
            return this;
        }
    }

}
