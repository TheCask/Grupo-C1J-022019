package ar.edu.unq.desapp.groupj.backend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Date deliveryDate;
    private List<OrderDetail> details = new ArrayList<OrderDetail>();

    private Order() {}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public void addDetail(OrderDetail detail) {
        this.details.add(detail);
    }

    public void removeDetail(OrderDetail detail) {
        this.details.remove(detail);
    }

    public int getRequestedAmount() {
        return this.details.stream().
                map( x -> x.getRequestedAmount() ).
                reduce(0,Integer::sum );
    }

    // TODO
    public void cancelAndNotify() {
    }

    public static class Builder {
        private int id;
        private Date deliveryDate;
        private List<OrderDetail> details = new ArrayList<OrderDetail>();

        private Builder() {}

        public static Builder anOrder() {
            return new Builder();
        }


        public Order build() {
            Order order = new Order();

            order.setId(this.id);
            order.setDeliveryDate(this.deliveryDate);
            order.setDetails(this.details);

            return order;
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withDeliveryDate(Date date) {
            this.deliveryDate = date;
            return this;
        }

        public Builder withDetails(List<OrderDetail> details) {
            this.details = details;
            return this;
        }

    }
}
