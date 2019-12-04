package ar.edu.unq.desapp.groupj.backend.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;

@Entity
@Table(name="users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private static final int BANNED_MENUS_TO_BE_BANNED = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private String city;
    private String address;
    private Double credit;

    @OneToMany(
            mappedBy = "provider",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<FoodService> foodServices = new HashSet<>();

    public User() {}

    public User(String firstName, String lastName, String mail, String phone, String city, String address, Double credit) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setMail(mail);
        this.setPhone(phone);
        this.setCity(city);
        this.setAddress(address);
        this.setCredit(credit);
    }

    public Double chargeCredit(Double credit) {
        if (credit > 0.0) {
            this.setCredit(this.getCredit() + credit);
            return this.getCredit();
        }
        else { throw new IllegalArgumentException("The credit to charge has to be greater than 0"); }
    }

    public Double withdrawCredit(Double credit) {
        Double newCredit = this.getCredit() - credit;
        if (newCredit >= 0 && newCredit < this.getCredit()) {
            this.setCredit(this.getCredit() - credit);
            return this.getCredit();
        }
        else { throw new IllegalArgumentException("The account has insufficient credits or credit to charge is not greater than 0"); }
    }

    public void transferToUser(User user, Double credit) {
        this.withdrawCredit(credit);
        user.chargeCredit(credit);
    }

    public void postFoodService(FoodService aService) {
        aService.setProvider(this);
        this.getFoodServices().add(aService);
    }

    public boolean equals(Object aUser) {
        if (aUser == null || this.getClass() != aUser.getClass()) return false;
        User user = (User) aUser;
        return this.getMail().equals(user.getMail());
    }

    public List<Menu> getMenusByName(String name) {
        List<Menu> results = new ArrayList<>();

        this.getFoodServices().forEach(service -> results.addAll( service.getMenusByName(name) ));

        return results;
    }

    public List<Menu> getMenusByCategory(MenuCategory category) {
        List<Menu> results = new ArrayList<>();

        this.getFoodServices().forEach(service -> results.addAll( service.getMenusByCategory(category) ) );

        return results;
    }

    public List<Menu> getMenusByCity(String city) {
        List<Menu> results = new ArrayList<>();

        this.getFoodServices().forEach(service -> results.addAll( service.getMenusByCity(city) ));

        return results;
    }

    public Order placeClientOrder(User aClient, FoodService aFoodService,
                                  Menu aMenu, LocalDate deliveryDate,
                                  LocalTime deliveryTime, DeliveryType deliveryType, int amount) {
        if( !aFoodService.getProvider().equals(this)) // this.getFoodServices().contains(aFoodService)
            throw new IllegalArgumentException("Servicio no publicado por el usuario proveedor.");

        Order anOrder = aFoodService.placeClientOrder(aClient,aMenu,deliveryDate,deliveryTime,deliveryType,amount);

        this.chargeCredit( aMenu.computeTotalCost(amount, deliveryType) );

        return anOrder;
    }

    public boolean isBanned() {
        return this.getMenus().stream().filter(menu -> menu.banned()).count() >= BANNED_MENUS_TO_BE_BANNED;
    }

    public boolean hasBannedMenus() {
        return this.getMenus().stream().
                reduce(false, (partialIsBanned, menu) -> partialIsBanned || menu.banned(), Boolean::logicalOr);
    }

    //@JsonIgnore
    @JsonManagedReference
    public List<Menu> getMenus() {
        return this.getFoodServices().stream().flatMap(service -> service.getMenus().stream()).collect(Collectors.toList());
    }

    public void notifyBan(Menu aMenu) {
        // TODO
        //notify banned menu
        //if (this.isBanned()) { } // notify banned provider
    }

    public void confirmOrders() {
        this.getFoodServices().forEach(service -> { if( service!=null) service.confirmOrders(); } );
    }

    //GETTERS

    public Integer getId() { return this.id; }
    public Double getCredit() { return this.credit; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getMail() { return this.mail; }
    public String getPhone() { return this.phone; }
    public String getCity() { return this.city; }
    public String getAddress() { return this.address; }
    @JsonManagedReference
    public Set<FoodService> getFoodServices() {
        return this.foodServices;
    }

    public void setId(Integer id) { this.id = id; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCredit(Double credit) {
        this.credit = credit;
    }
    public void setFoodServices(Set<FoodService> foodServices) { this.foodServices=foodServices; }

}