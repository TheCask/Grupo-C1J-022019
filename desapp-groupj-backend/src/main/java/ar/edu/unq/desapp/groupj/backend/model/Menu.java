package ar.edu.unq.desapp.groupj.backend.model;

import java.util.Date;

public class Menu {
    private Business        business;
    private String          name;
    private String          description;
    private MenuCategory    category;
    private float           deliveryValue;
    private Date            availableFrom;
    private Date            availableTo;
    private DeliveryShift[]   deliveryShifts;
    private float           averageDeliveryTime;
    private float           price; //?
    private int             minimumAmount1;
    private float           minimumAmount1Price;
    private int             minimumAmount2;
    private float           minimumAmount2Price;
    private int             maximumDailySales;

    private Menu() {}

    public Menu(Business business, String name, String description,
            MenuCategory category, float deliveryValue, Date availableFrom,
            Date availableTo, DeliveryShift deliveryShifts[], float averageDeliveryTime,
            float price, int minimumAmount1, float minimumAmount1Price,
            int minimumAmount2, float minimumAmount2Price, int maximumDailySales ) {
        this.setBusiness(business);
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

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
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

    public float getDeliveryValue() {
        return deliveryValue;
    }

    public void setDeliveryValue(float deliveryValue) {
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

    public float getAverageDeliveryTime() {
        return averageDeliveryTime;
    }

    public void setAverageDeliveryTime(float averageDeliveryTime) {
        this.averageDeliveryTime = averageDeliveryTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMinimumAmount1() {
        return minimumAmount1;
    }

    public void setMinimumAmount1(int minimumAmount1) {
        this.minimumAmount1 = minimumAmount1;
    }

    public float getMinimumAmount1Price() {
        return minimumAmount1Price;
    }

    public void setMinimumAmount1Price(float minimumAmount1Price) {
        this.minimumAmount1Price = minimumAmount1Price;
    }

    public int getMinimumAmount2() {
        return minimumAmount2;
    }

    public void setMinimumAmount2(int minimumAmount2) {
        this.minimumAmount2 = minimumAmount2;
    }

    public float getMinimumAmount2Price() {
        return minimumAmount2Price;
    }

    public void setMinimumAmount2Price(float minimumAmount2Price) {
        this.minimumAmount2Price = minimumAmount2Price;
    }

    public int getMaximumDailySales() {
        return maximumDailySales;
    }

    public void setMaximumDailySales(int maximumDailySales) {
        this.maximumDailySales = maximumDailySales;
    }


    public static class Builder {
        private Business        business;
        private String          name;
        private String          description;
        private MenuCategory    category;
        private float           deliveryValue;
        private Date            availableFrom;
        private Date            availableTo;
        private DeliveryShift   deliveryShifts[];
        private float           averageDeliveryTime;
        private float           price;
        private int             minimumAmount1;
        private float           minimumAmount1Price;
        private int             minimumAmount2;
        private float           minimumAmount2Price;
        private int             maximumDailySales;

        public Builder(Business business, String name) {
            this.business = business;
            this.name = name;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withCategory(MenuCategory category) {
            this.category = category;
            return this;
        }

        public Builder withDeliveryValue(float deliveryValue) {
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

        public Builder withPrice(float price) {
            this.price = price;
            return this;
        }

        public Builder withMinimumAmount1(int minimumAmount1) {
            this.minimumAmount1 = minimumAmount1;
            return this;
        }

        public Builder withMinimumAmount1Price(float minimumAmount1Price) {
            this.minimumAmount1Price = minimumAmount1Price;
            return this;
        }

        public Builder withMinimumAmount2(int minimumAmount2) {
            this.minimumAmount2 = minimumAmount2;
            return this;
        }

        public Builder withMinimumAmount2Price(float minimumAmount2Price) {
            this.minimumAmount2Price = minimumAmount2Price;
            return this;
        }

        public Builder withMaximumDailySales(int maximumDailySales) {
            this.maximumDailySales = maximumDailySales;
            return this;
        }

        public Menu build() {
            return new Menu(this.business,
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