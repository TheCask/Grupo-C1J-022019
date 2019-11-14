package ar.edu.unq.desapp.groupj.backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {

    private int id;
    private Menu menu;
    private LocalDate deliveryDate;
    private Set<OrderDetail> details = new HashSet<>();

    private Order() {}

    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Set<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<OrderDetail> details) {
        this.details = details;
    }

    public void addDetail(OrderDetail detail) {
        this.details.add(detail);
    }

    public void removeDetail(OrderDetail detail) {
        this.details.remove(detail);
    }

    public User getProvider() { return this.getMenu().getProvider(); }

    public int getRequestedAmount() {
        return this.details.stream().
                map( x -> x.getRequestedAmount() ).
                reduce(0,Integer::sum );
    }

    public void cancelOrder() {
        //TODO
    }

    public void confirmOrder() {
        int requestedAmount = this.getDetails().stream().
                reduce(0, (partialAmount, detail) -> partialAmount + detail.getRequestedAmount(), Integer::sum);
        final double creditToReturn;
        Menu aMenu = this.getMenu();

        if (requestedAmount >= aMenu.getMinimumAmount2()) {
            creditToReturn = aMenu.getPrice() - aMenu.getMinimumAmount2Price();
        }
        else if (requestedAmount >= aMenu.getMinimumAmount1()) {
            creditToReturn = aMenu.getPrice() - aMenu.getMinimumAmount1Price();
        }
        else { creditToReturn = 0; }

        this.getDetails().forEach(detail -> detail.confirmOrderToUser(this.deliveryDate, creditToReturn));
    }

    public static class Builder {
        private int id;
        private Menu menu;
        private LocalDate deliveryDate;
        private Set<OrderDetail> details = new HashSet<>();

        private Builder() {}

        public static Builder anOrder() {
            return new Builder();
        }

        public Order build() {
            Order order = new Order();

            order.setId(this.id);
            order.setMenu(this.menu);
            order.setDeliveryDate(this.deliveryDate);
            order.setDetails(this.details);

            return order;
        }

        public Builder withMenu(Menu menu) {
            this.menu = menu;
            return this;
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withDeliveryDate(LocalDate date) {
            this.deliveryDate = date;
            return this;
        }

        public Builder withDetails(HashSet<OrderDetail> details) {
            this.details = details;
            return this;
        }

    }
}
