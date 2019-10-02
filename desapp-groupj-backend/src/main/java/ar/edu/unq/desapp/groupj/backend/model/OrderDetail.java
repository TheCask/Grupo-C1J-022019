package ar.edu.unq.desapp.groupj.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrderDetail {
    private User user;
    private LocalTime deliveryTime;
    private DeliveryType deliveryType;
    private int requestedAmount;

    private OrderDetail() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void confirmOrderToUser(LocalDate deliveryDate, double creditToReturn, User aProvider) {
        if (creditToReturn !=0) {
            double credit = creditToReturn * this.getRequestedAmount();
            this.getUser().chargeCredit((int)credit);
            aProvider.withdrawCredit((int)credit);
        }
        // TODO notify provider and client
    }


    public static class Builder {
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

            detail.setUser(this.user);
            detail.setDeliveryTime(this.deliveryTime);
            detail.setDeliveryType(this.deliveryType);
            detail.setRequestedAmount(this.requestedAmount);

            return detail;
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
