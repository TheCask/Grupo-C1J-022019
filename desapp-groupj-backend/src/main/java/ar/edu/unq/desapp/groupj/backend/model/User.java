package ar.edu.unq.desapp.groupj.backend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private String city;
    private String address;
    private int credit;

    private List<Service> services = new ArrayList<Service>();

    public User(String firstName, String lastName, String mail, String phone, String city, String address, int credit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.city = city;
        this.address = address;
        this.credit = credit;
    }

    public int chargeCredit(int credit) {
        if (credit > 0) { this.credit += credit; }
        return this.credit;
    }

    public int withdrawCredit(int credit) {
        int newCredit = this.getCredit() - credit;
        if (newCredit >= 0) { this.setCredit(newCredit); }
        return this.getCredit();
    }

    public void postService(Service aService) { this.services.add(aService); }

    public boolean equals(Object aUser) {
        if (aUser == null || this.getClass() != aUser.getClass()) return false;
        User user = (User) aUser;
        return this.getMail().equals(user.getMail());
    }

    public List<Menu> getMenusByName(String name) {
        List<Menu> results = new ArrayList<Menu>();

        this.getServices().forEach( service -> results.addAll( service.getMenusByName(name) ));

        return results;
    }

    public List<Menu> getMenusByCategory(MenuCategory category) {
        List<Menu> results = new ArrayList<Menu>();

        this.getServices().forEach( service -> results.addAll( service.getMenusByCategory(category) ) );

        return results;
    }

    public List<Menu> getMenusByCity(String city) {
        List<Menu> results = new ArrayList<Menu>();

        this.getServices().forEach( service -> results.addAll( service.getMenusByCity(city) ));

        return results;
    }

    //GETTERS

    public int getCredit() { return this.credit; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getMail() { return this.mail; }
    public String getPhone() { return this.phone; }
    public String getCity() { return this.city; }
    public String getAddress() { return this.address; }
    public List<Service> getServices() { return services; }

    // SETTERS

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setMail(String mail) { this.mail = mail; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setCity(String city) { this.city = city; }
    public void setAddress(String address) { this.address = address; }
    public void setCredit(int credit) { this.credit = credit; }

    public void placeClientOrder(User aClient, Service aService, Menu aMenu, Date deliveryDate, DeliveryType deliveryType, int amount) {
        if( !this.getServices().contains(aService) )
            throw new IllegalArgumentException("Servicio no publicado por el usuario proveedor.");

        aService.placeClientOrder(aClient,aMenu,deliveryDate,deliveryType,amount);
    }
}