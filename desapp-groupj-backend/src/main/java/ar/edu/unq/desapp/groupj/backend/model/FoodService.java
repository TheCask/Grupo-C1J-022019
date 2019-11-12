package ar.edu.unq.desapp.groupj.backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FoodService {
    private static final int MIN_DESCRIPTION_LENGTH = 30;
    private static final int MAX_DESCRIPTION_LENGTH = 200;
    private static final int MAX_MENUS = 20;

    private int id;
    private String name;
    private String city;
    private String address;
    private String description;
    private String site;
    private String mail;
    private String phone;
    private int deliveryZone;
    private Set<Menu> menus = new HashSet<Menu>();

    public FoodService() {}

    public FoodService(String name, String city, String address, String description, String mail, String phone, int deliveryZone) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.description = description;
        this.mail = mail;
        this.phone = phone;
        this.deliveryZone = deliveryZone;
    }

    public FoodService addSite(String aSite) {
        this.site = aSite;
        return this;
    }

    public void addMenu(Menu aMenu) {
        if (this.menus.stream().filter(x -> x.active()).count() >= this.MAX_MENUS) {
            throw new IllegalArgumentException("Se alcanzó el límite máximo de menus válidos");
        }
        this.menus.add(aMenu);
    }

    public void deleteMenu(Menu aMenu) { this.menus.remove(aMenu); }

    public void updateMenu(Menu aMenuToUpdate, Menu updatedMenu) {
        this.menus.remove(aMenuToUpdate);
        this.menus.add(updatedMenu);
    }

    public List<Menu> getMenusByName(String name) {
        return this.getMenus().stream().filter(m -> m.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Menu> getMenusByCategory(MenuCategory category) {
        return this.getMenus().stream().filter(m -> m.getCategory().equals(category)).collect(Collectors.toList());
    }

    public Set<Menu> getMenusByCity(String city) {
        if( this.city.equals(city) )
            return this.getMenus();
        return null;
    }

    public Order placeClientOrder(User aClient, Menu aMenu, LocalDate deliveryDate, DeliveryType deliveryType, int amount) {
        if( !this.getMenus().contains(aMenu) )
            throw new IllegalArgumentException("Menu no forma parte del servicio.");

        return aMenu.placeClientOrder(aClient,deliveryDate,deliveryType,amount);
    }

    public void confirmOrders(User aProvider) {
        this.getMenus().forEach(menu -> menu.confirmOrders(aProvider));
    }

    // GETTERS
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public String getDescription() { return description; }
    public String getSite() { return site; }
    public String getMail() { return mail; }
    public String getPhone() { return phone; }
    public int getDeliveryZone() { return deliveryZone; }
    public Set<Menu> getMenus() {
        return this.menus;
    }

    // SETTERS

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) {
        ValidatorsUtils.validateStringLength(description,MIN_DESCRIPTION_LENGTH,MAX_DESCRIPTION_LENGTH,"Description");
        this.description = description;
    }
    public void setMail(String mail) {
        ValidatorsUtils.validateMail(mail, "mail");
        this.mail = mail;
    }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDeliveryZone(int deliveryZone) { this.deliveryZone = deliveryZone; }
    public void setMenus( HashSet<Menu> menus ) { this.menus = menus; }
    public void setCity( String city ) { this.city = city; }
    public void setAddress( String address ) { this.address = address; }
    public void setSite( String site ) { this.site = site; }
}