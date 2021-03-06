package ar.edu.unq.desapp.groupj.backend.model;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name="menus")
public class Menu {
    private static final Integer MIN_NAME_LENGTH = 4;
    private static final Integer MAX_NAME_LENGTH = 30;
    private static final Integer MIN_DESCRIPTION_LENGTH = 20;
    private static final Integer MAX_DESCRIPTION_LENGTH = 40;
    private static final Double MIN_DELIVERY_VALUE = 10.0;
    private static final Double MAX_DELIVERY_VALUE = 40.0;
    private static final Integer BOTTOM_MINIMUM_AMOUNT_1 = 10;
    private static final Integer TOP_MINIMUM_AMOUNT_1 = 70;
    private static final Integer BOTTOM_MINIMUM_AMOUNT_2 = 40;
    private static final Integer TOP_MINIMUM_AMOUNT_2 = 150;
    private static final Double BOTTOM_MINIMUM_AMOUNT_PRICE = 0.0;
    private static final Double TOP_MINIMUM_AMOUNT_PRICE = 1000.0;
    public static final Integer MINIMUM_DAYS_TO_DELIVERY = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer         id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_service_id")
    private FoodService foodService;

    private String          name;
    private String          description;
    @Transient
    private MenuCategory    category;
    private String          city;
    private Double          deliveryValue;
    @Transient
    private LocalDate       availableFrom;
    @Transient
    private LocalDate       availableTo;

    @Transient
    private Set<DeliveryShift> deliveryShifts = new HashSet<>();

    private Double          averageDeliveryTime;
    private Double          price;
    private Integer             minimumAmount1;
    private Double          minimumAmount1Price;
    private Integer             minimumAmount2;
    private Double          minimumAmount2Price;
    private Integer             maximumDailySales;

    @OneToMany(
            mappedBy = "menu",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Rate>       rates = new HashSet<>();

    @Transient
    private Set<Order>      orders = new HashSet<>();

    private String          imageUrl;


    private Menu() {}

    public Integer getId() { return this.id; }

    public void setId(Integer save) { this.id = save; }

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

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public Double getDeliveryValue() {
        return deliveryValue;
    }

    public void setDeliveryValue(Double deliveryValue) {
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

    public Set<DeliveryShift> getDeliveryShifts() {
        return deliveryShifts;
    }

    public void setDeliveryShifts(Set<DeliveryShift> deliveryShifts) {
        this.deliveryShifts = deliveryShifts;
    }

    public Double getAverageDeliveryTime() {
        return averageDeliveryTime;
    }

    public void setAverageDeliveryTime(Double averageDeliveryTime) {
        this.averageDeliveryTime = averageDeliveryTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) { this.price = price; }

    public Integer getMinimumAmount1() {
        return minimumAmount1;
    }

    public void setMinimumAmount1(Integer minimumAmount1) {
        ValidatorsUtils.validateIntValue(minimumAmount1,BOTTOM_MINIMUM_AMOUNT_1,TOP_MINIMUM_AMOUNT_1,"Minimum Amount 1");
        this.minimumAmount1 = minimumAmount1;
    }

    public Double getMinimumAmount1Price() {
        return minimumAmount1Price;
    }

    public void setMinimumAmount1Price(Double minimumAmount1Price) {
        ValidatorsUtils.validateDoubleValue(minimumAmount1Price,BOTTOM_MINIMUM_AMOUNT_PRICE,TOP_MINIMUM_AMOUNT_PRICE,"Minimum Amount 1 Price");
        this.minimumAmount1Price = minimumAmount1Price;
    }

    public Integer getMinimumAmount2() {
        return minimumAmount2;
    }

    public void setMinimumAmount2(Integer minimumAmount2) {
        ValidatorsUtils.validateIntValue(minimumAmount2,BOTTOM_MINIMUM_AMOUNT_2,TOP_MINIMUM_AMOUNT_2,"Minimum Amount 2");
        this.minimumAmount2 = minimumAmount2;
    }

    public Double getMinimumAmount2Price() {
        return minimumAmount2Price;
    }

    public void setMinimumAmount2Price(Double minimumAmount2Price) {
        ValidatorsUtils.validateDoubleValue(minimumAmount2Price,BOTTOM_MINIMUM_AMOUNT_PRICE,TOP_MINIMUM_AMOUNT_PRICE,"Minimum Amount 1 Price");
        this.minimumAmount2Price = minimumAmount2Price;
    }

    public Integer getMaximumDailySales() {
        return maximumDailySales;
    }

    public void setMaximumDailySales(Integer maximumDailySales) {
        this.maximumDailySales = maximumDailySales;
    }

    @JsonManagedReference
    public Set<Rate> getRates() { return this.rates; }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    @JsonBackReference
    public FoodService getFoodService() { return this.foodService; }

    public void setFoodService(FoodService aService) { this.foodService = aService; }

    @JsonBackReference
    public User getProvider() { return this.getFoodService().getProvider(); }

    @JsonIgnore
    public Set<Order> getOrders() {
        return this.orders;
    }

    public String getImageUrl() { return this.imageUrl; }

    public void setImageUrl( String imageUrl ) { this.imageUrl = imageUrl; }

    public void addOrder(Order anOrder) { this.orders.add(anOrder); }

    public void addRate(Rate rate) {
        this.rates.add(rate);
    }

    @JsonIgnore
    public Integer getRateCount() { return this.rates.size(); }

    @JsonIgnore
    public Integer getAverageRate() {
        Integer rateCount = getRateCount();
        Integer rateSum = this.rates.stream().
                                map( x -> x.getValue() ).
                                reduce(0,Integer::sum );
        return rateSum / (rateCount>0?rateCount:1);
    }

    public boolean active() {
        LocalDate now = LocalDate.now();
        return now.compareTo(this.availableFrom) >= 0 && now.compareTo(this.availableTo) <= 0;
    }

    public boolean banned() { return (this.getAverageRate() <= 2 && this.getRateCount() >= 20); }

    public Order placeClientOrder( User aClient, LocalDate deliveryDate, LocalTime deliverytime,
                                   DeliveryType deliveryType, Integer amount) {
        ValidatorsUtils.validateDeliveryDate(deliveryDate,MINIMUM_DAYS_TO_DELIVERY);

        aClient.withdrawCredit( computeTotalCost( amount, deliveryType ) );

        List<Order> ordersInDeliveryDate = this.orders.stream().filter( order -> order.getDeliveryDate()==deliveryDate ).collect(Collectors.toList());
        Order anOrder;

        if( ordersInDeliveryDate.size() == 0 ) {
            anOrder = Order.Builder.anOrder().withMenu(this).withDeliveryDate(deliveryDate).build();
            this.addOrder(anOrder);
        }
        else
            anOrder = ordersInDeliveryDate.get(0);

        OrderDetail anOrderDetail = OrderDetail.Builder.anOrderDetail().
                withOrder(anOrder).
                withUser(aClient).
                withDeliveryTime(deliverytime).
                withDeliveryType(deliveryType).
                withRequestedAmount(amount).
                withStatus(OrderStatus.Pending).
                build();

        anOrder.addDetail(anOrderDetail);

        return anOrder;
    }

    public void cancelAllOrders() { this.orders.forEach(order -> order.cancelOrder()); }

    public void confirmOrders() {
        Stream<Order> ordersToConfirmToday = this.getOrders().stream().
                filter(order -> order.getDeliveryDate().getDayOfYear() == LocalDate.now().getDayOfYear());

        ordersToConfirmToday.forEach(order -> order.confirmOrder());
    }

    public Double computeTotalCost( Integer quantity, DeliveryType deliveryType ) {
        return (quantity * computePriceForQuantity(quantity)) + computeDeliveryCost(deliveryType);
    }

    public Double computePriceForQuantity( Integer quantity ) {
        return (quantity < this.getMinimumAmount1() ? this.getPrice() :
                (quantity >= this.getMinimumAmount2()? this.getMinimumAmount2Price() : this.getMinimumAmount1Price() ));
    }

    public Double computeDeliveryCost( DeliveryType deliveryType ) {
        return ( deliveryType==DeliveryType.DeliverToAddress ? this.getDeliveryValue() : 0 );
    }

    public static class Builder {
        private FoodService     service;
        private String          name;
        private String          description;
        private MenuCategory    category;
        private Double          deliveryValue;
        private LocalDate       availableFrom;
        private LocalDate       availableTo;
        private Set<DeliveryShift>   deliveryShifts;
        private Double          averageDeliveryTime;
        private Double          price;
        private Integer             minimumAmount1;
        private Double          minimumAmount1Price;
        private Integer             minimumAmount2;
        private Double          minimumAmount2Price;
        private Integer             maximumDailySales;

        private Builder(){}

        public static Builder aMenu() {
            return new Builder();
        }

        public Builder withService(FoodService aService) {
            this.service = aService;
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

        public Builder withDeliveryValue(Double deliveryValue) {
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

        public Builder withDeliveryShifts(Set<DeliveryShift> deliveryShifts) {
            this.deliveryShifts = deliveryShifts;
            return this;
        }

        public Builder withAverageDeliveryTime(Double averageDeliveryTime) {
            this.averageDeliveryTime = averageDeliveryTime;
            return this;
        }

        public Builder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder withMinimumAmount1(Integer minimumAmount1) {
            this.minimumAmount1 = minimumAmount1;
            return this;
        }

        public Builder withMinimumAmount1Price(Double minimumAmount1Price) {
            this.minimumAmount1Price = minimumAmount1Price;
            return this;
        }

        public Builder withMinimumAmount2(Integer minimumAmount2) {
            this.minimumAmount2 = minimumAmount2;
            return this;
        }

        public Builder withMinimumAmount2Price(Double minimumAmount2Price) {
            this.minimumAmount2Price = minimumAmount2Price;
            return this;
        }

        public Builder withMaximumDailySales(Integer maximumDailySales) {
            this.maximumDailySales = maximumDailySales;
            return this;
        }

        public Menu build() {
            Menu menu = new Menu();

            menu.setFoodService(this.service);
            menu.setName(this.name);
            menu.setDescription(this.description);
            menu.setCategory(this.category);
            menu.setAvailableFrom(this.availableFrom);
            menu.setAvailableTo(this.availableTo);
            menu.setDeliveryShifts(this.deliveryShifts);
            menu.setAverageDeliveryTime(this.averageDeliveryTime);
            menu.setPrice(this.price);
            menu.setMinimumAmount1(this.minimumAmount1);
            menu.setMinimumAmount1Price(this.minimumAmount1Price);
            menu.setMaximumDailySales(this.maximumDailySales);

            if (this.minimumAmount2Price > 0 && this.minimumAmount2 > 0) {
                menu.setMinimumAmount2Price(this.minimumAmount2Price);
                menu.setMinimumAmount2(this.minimumAmount2);
            }
            if (this.deliveryValue > 0) { menu.setDeliveryValue(this.deliveryValue); }

            ValidatorsUtils.validateMenuPrices(menu);
            ValidatorsUtils.validateMenuAmounts(menu);

            return menu;
        }
    }
}