package ar.edu.unq.desapp.groupj.backend.model;

import java.util.Date;

public class Menu {
    private Service         service;
    private String          name;
    private String          description;
    private MenuCategory    category;
    private double           deliveryValue;
    private Date            availableFrom;
    private Date            availableTo;
    private DeliveryShift[]   deliveryShifts;
    private double           averageDeliveryTime;
    private double           price; //?
    private int             minimumAmount1;
    private double           minimumAmount1Price;
    private int             minimumAmount2;
    private double           minimumAmount2Price;
    private int             maximumDailySales;

    private Menu() {}

    public Menu(Service service, String name, String description,
            MenuCategory category, double deliveryValue, Date availableFrom,
            Date availableTo, DeliveryShift deliveryShifts[], double averageDeliveryTime,
            double price, int minimumAmount1, double minimumAmount1Price,
            int minimumAmount2, double minimumAmount2Price, int maximumDailySales ) {
        this.setService(service);
        this.setName(name);
        this.setDescription(description);
        this.setCategory(category);
        this.setDeliveryValue(deliveryValue);
        this.setAvailableFrom(availableFrom);
        this.setAvailableTo(availableTo);
        this.setDeliveryShifts(deliveryShifts);
        this.setAverageDeliveryTime(averageDeliveryTime);
        this.setPrice(price);
        this.setMinimumAmount1(minimumAmount1);
        this.setMinimumAmount1Price(minimumAmount1Price);
        this.setMinimumAmount2(minimumAmount2);
        this.setMinimumAmount2Price(minimumAmount2Price);
        this.setMaximumDailySales(maximumDailySales);
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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
        this.deliveryValue = deliveryValue;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Date getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }

    public DeliveryShift[] getDeliveryShifts() {
        return deliveryShifts;
    }

    public void setDeliveryShifts(DeliveryShift[] deliveryShifts) {
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
        this.minimumAmount1 = minimumAmount1;
    }

    public double getMinimumAmount1Price() {
        return minimumAmount1Price;
    }

    public void setMinimumAmount1Price(double minimumAmount1Price) {
        this.minimumAmount1Price = minimumAmount1Price;
    }

    public int getMinimumAmount2() {
        return minimumAmount2;
    }

    public void setMinimumAmount2(int minimumAmount2) {
        this.minimumAmount2 = minimumAmount2;
    }

    public double getMinimumAmount2Price() {
        return minimumAmount2Price;
    }

    public void setMinimumAmount2Price(double minimumAmount2Price) {
        this.minimumAmount2Price = minimumAmount2Price;
    }

    public int getMaximumDailySales() {
        return maximumDailySales;
    }

    public void setMaximumDailySales(int maximumDailySales) {
        this.maximumDailySales = maximumDailySales;
    }


    public static class Builder {
        private Service         service;
        private String          name;
        private String          description;
        private MenuCategory    category;
        private double           deliveryValue;
        private Date            availableFrom;
        private Date            availableTo;
        private DeliveryShift   deliveryShifts[];
        private double           averageDeliveryTime;
        private double           price;
        private int             minimumAmount1;
        private double           minimumAmount1Price;
        private int             minimumAmount2;
        private double           minimumAmount2Price;
        private int             maximumDailySales;

        private Builder(){}

        public static Builder aMenu() {
            return new Builder();
        }

        public Builder withService(Service service) {
            this.service = service;
            return this;
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

        public Builder withAvailableFrom(Date availableFrom) {
            this.availableFrom = availableFrom;
            return this;
        }

        public Builder withAvailableTo(Date availableTo) {
            this.availableTo = availableTo;
            return this;
        }

        public Builder withDeliveryShifts(DeliveryShift deliveryShifts[]) {
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
            return new Menu(this.service,
                            this.name,
                            this.description,
                            this.category ,
                            this.deliveryValue,
                            this.availableFrom,
                            this.availableTo,
                            this.deliveryShifts,
                            this.averageDeliveryTime,
                            this.price,
                            this.minimumAmount1,
                            this.minimumAmount1Price,
                            this.minimumAmount2,
                            this.minimumAmount2Price,
                            this.maximumDailySales);
        }
    }
}