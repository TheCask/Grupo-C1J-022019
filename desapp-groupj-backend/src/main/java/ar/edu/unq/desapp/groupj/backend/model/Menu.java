package ar.edu.unq.desapp.groupj.backend.model;

import ar.edu.unq.desapp.groupj.backend.model.exception.MenuException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_NAME_LENGTH = 30;
    private static final int MIN_DESCRIPTION_LENGTH = 20;
    private static final int MAX_DESCRIPTION_LENGTH = 40;
    private static final double MIN_DELIVERY_VALUE = 10.0;
    private static final double MAX_DELIVERY_VALUE = 40.0;
    private static final int BOTTOM_MINIMUM_AMOUNT_1 = 10;
    private static final int TOP_MINIMUM_AMOUNT_1 = 70;
    private static final int BOTTOM_MINIMUM_AMOUNT_2 = 40;
    private static final int TOP_MINIMUM_AMOUNT_2 = 150;
    private static final double BOTTOM_MINIMUM_AMOUNT_PRICE = 0.0;
    private static final double TOP_MINIMUM_AMOUNT_PRICE = 1000.0;
    private static final int MINIMUM_DAYS_TO_DELIVERY = 2;

    private String          name;
    private String          description;
    private MenuCategory    category;
    private double          deliveryValue;
    private LocalDate       availableFrom;
    private LocalDate       availableTo;
    private List<DeliveryShift> deliveryShifts;
    private double          averageDeliveryTime;
    private double          price; //?
    private int             minimumAmount1;
    private double          minimumAmount1Price;
    private int             minimumAmount2;
    private double          minimumAmount2Price;
    private int             maximumDailySales;
    private List<Rate>      rates = new ArrayList<Rate>();
    private List<Order>     orders = new ArrayList<Order>();

    private Menu() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidatorsUtils.validateStringLength(name,MIN_NAME_LENGTH,MAX_NAME_LENGTH,"Name");
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        ValidatorsUtils.validateStringLength(description,MIN_DESCRIPTION_LENGTH,MAX_DESCRIPTION_LENGTH,"Description");
        this.description = description;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public void setCategory(MenuCategory category) {
        this.category = category;
    }

    public double getDeliveryValue() {
        return deliveryValue;
    }

    public void setDeliveryValue(double deliveryValue) {
        ValidatorsUtils.validateDoubleValue(deliveryValue,MIN_DELIVERY_VALUE,MAX_DELIVERY_VALUE,"Delivery Value");
        this.deliveryValue = deliveryValue;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDate getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(LocalDate availableTo) {
        this.availableTo = availableTo;
    }

    public List<DeliveryShift> getDeliveryShifts() {
        return deliveryShifts;
    }

    public void setDeliveryShifts(List<DeliveryShift> deliveryShifts) {
        this.deliveryShifts = deliveryShifts;
    }

    public double getAverageDeliveryTime() {
        return averageDeliveryTime;
    }

    public void setAverageDeliveryTime(double averageDeliveryTime) {
        this.averageDeliveryTime = averageDeliveryTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMinimumAmount1() {
        return minimumAmount1;
    }

    public void setMinimumAmount1(int minimumAmount1) {
        ValidatorsUtils.validateIntValue(minimumAmount1,BOTTOM_MINIMUM_AMOUNT_1,TOP_MINIMUM_AMOUNT_1,"Minimum Amount 1");
        this.minimumAmount1 = minimumAmount1;
    }

    public double getMinimumAmount1Price() {
        return minimumAmount1Price;
    }

    public void setMinimumAmount1Price(double minimumAmount1Price) {
        ValidatorsUtils.validateDoubleValue(minimumAmount1Price,BOTTOM_MINIMUM_AMOUNT_PRICE,TOP_MINIMUM_AMOUNT_PRICE,"Minimum Amount 1 Price");
        this.minimumAmount1Price = minimumAmount1Price;
    }

    public int getMinimumAmount2() {
        return minimumAmount2;
    }

    public void setMinimumAmount2(int minimumAmount2) {
        ValidatorsUtils.validateIntValue(minimumAmount2,BOTTOM_MINIMUM_AMOUNT_2,TOP_MINIMUM_AMOUNT_2,"Minimum Amount 2");
        this.minimumAmount2 = minimumAmount2;
    }

    public double getMinimumAmount2Price() {
        return minimumAmount2Price;
    }

    public void setMinimumAmount2Price(double minimumAmount2Price) {
        ValidatorsUtils.validateDoubleValue(minimumAmount2Price,BOTTOM_MINIMUM_AMOUNT_PRICE,TOP_MINIMUM_AMOUNT_PRICE,"Minimum Amount 1 Price");
        this.minimumAmount2Price = minimumAmount2Price;
    }

    public int getMaximumDailySales() {
        return maximumDailySales;
    }

    public void setMaximumDailySales(int maximumDailySales) {
        this.maximumDailySales = maximumDailySales;
    }

    public List<Rate> getRates() { return this.rates; }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public void addRate(Rate rate) {
        this.rates.add(rate);
    }

    public int getRateCount() { return this.rates.size(); }

    public int getAverageRate() {
        int rateSum = this.rates.stream().
                                map( x -> x.getValue() ).
                                reduce(0,Integer::sum );
        return rateSum / getRateCount();
    }

    public boolean active() {
        LocalDate now = LocalDate.now();
        return now.compareTo(this.availableFrom) >= 0 && now.compareTo(this.availableTo) <= 0;
    }

    public boolean isBanned() { return (this.getAverageRate() <= 2 && this.getRateCount() >= 20); }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public Order placeClientOrder(User aClient, LocalDate deliveryDate, DeliveryType deliveryType, int amount) {
        ValidatorsUtils.validateDeliveryDate(deliveryDate,MINIMUM_DAYS_TO_DELIVERY);

        aClient.withdrawCredit( (int)(this.getMinimumAmount1Price()*amount) );

        List<Order> ordersInDeliveryDate = this.orders.stream().filter( order -> order.getDeliveryDate()==deliveryDate ).collect(Collectors.toList());
        Order anOrder;

        if( ordersInDeliveryDate.size() == 0 ) {
            anOrder = Order.Builder.anOrder().withDeliveryDate(deliveryDate).build();
            this.orders.add(anOrder);
        }
        else
            anOrder = ordersInDeliveryDate.get(0);

        OrderDetail anOrderDetail = OrderDetail.Builder.anOrderDetail().
                withUser(aClient).
                withDeliveryType(deliveryType).
                withRequestedAmount(amount).
                build();

        anOrder.addDetail(anOrderDetail);

        return anOrder;
    }

    public void cancelOrders() { this.orders.forEach(order -> order.cancelAndNotify()); }


    public static class Builder {
        private String          name;
        private String          description;
        private MenuCategory    category;
        private double          deliveryValue;
        private LocalDate       availableFrom;
        private LocalDate       availableTo;
        private List<DeliveryShift>   deliveryShifts;
        private double          averageDeliveryTime;
        private double          price;
        private int             minimumAmount1;
        private double          minimumAmount1Price;
        private int             minimumAmount2;
        private double          minimumAmount2Price;
        private int             maximumDailySales;

        private Builder(){}

        public static Builder aMenu() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withCategory(MenuCategory category) {
            this.category = category;
            return this;
        }

        public Builder withDeliveryValue(double deliveryValue) {
            this.deliveryValue = deliveryValue;
            return this;
        }

        public Builder withAvailableFrom(LocalDate availableFrom) {
            this.availableFrom = availableFrom;
            return this;
        }

        public Builder withAvailableTo(LocalDate availableTo) {
            this.availableTo = availableTo;
            return this;
        }

        public Builder withDeliveryShifts(List<DeliveryShift> deliveryShifts) {
            this.deliveryShifts = deliveryShifts;
            return this;
        }

        public Builder withAverageDeliveryTime(int averageDeliveryTime) {
            this.averageDeliveryTime = averageDeliveryTime;
            return this;
        }

        public Builder withPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder withMinimumAmount1(int minimumAmount1) {
            this.minimumAmount1 = minimumAmount1;
            return this;
        }

        public Builder withMinimumAmount1Price(double minimumAmount1Price) {
            this.minimumAmount1Price = minimumAmount1Price;
            return this;
        }

        public Builder withMinimumAmount2(int minimumAmount2) {
            this.minimumAmount2 = minimumAmount2;
            return this;
        }

        public Builder withMinimumAmount2Price(double minimumAmount2Price) {
            this.minimumAmount2Price = minimumAmount2Price;
            return this;
        }

        public Builder withMaximumDailySales(int maximumDailySales) {
            this.maximumDailySales = maximumDailySales;
            return this;
        }

        public Menu build() {
            Menu menu = new Menu();

            menu.setName(this.name);
            menu.setDescription(this.description);
            menu.setCategory(this.category);
            menu.setDeliveryValue(this.deliveryValue);
            menu.setAvailableFrom(this.availableFrom);
            menu.setAvailableTo(this.availableTo);
            menu.setDeliveryShifts(this.deliveryShifts);
            menu.setAverageDeliveryTime(this.averageDeliveryTime);
            menu.setPrice(this.price);
            menu.setMinimumAmount1(this.minimumAmount1);
            menu.setMinimumAmount1Price(this.minimumAmount1Price);
            menu.setMinimumAmount2(this.minimumAmount2);
            menu.setMinimumAmount2Price(this.minimumAmount2Price);
            menu.setMaximumDailySales(this.maximumDailySales);

            return menu;
        }
    }
}