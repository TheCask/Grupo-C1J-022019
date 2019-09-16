package ar.edu.unq.desapp.groupj.backend.model;

import java.time.LocalTime;

public class OrderDetail {
    private Order order;
    private Client client;
    private LocalTime deliveryTime;
    private DeliveryType deliveryType;
    private int requestedAmount;

    private OrderDetail() {}

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }

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



    public static class Builder {
        private Order order;
        private Client client;
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
            detail.setClient(this.client);
            detail.setDeliveryTime(this.deliveryTime);
            detail.setDeliveryType(this.deliveryType);
            detail.setRequestedAmount(this.requestedAmount);

            return detail;
        }

        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder withClient(Client client) {
            this.client = client;
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
