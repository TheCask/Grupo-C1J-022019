package ar.edu.unq.desapp.groupj.backend.model;

public class Service {
    private String name;
    private String city;
    private String address;
    private String description;
    private String site;
    private String mail;
    private String phone;
    private int deliveryZone;

    public Service(String name, String city, String address, String description, String mail, String phone, int deliveryZone) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.description = description;
        this.mail = mail;
        this.phone = phone;
        this.deliveryZone = deliveryZone;
    }

    public Service addSite(String aSite) {
        this.site = aSite;
        return this;
    }

    // GETTERS

    public String getName() { return name; }

    public String getCity() { return city; }

    public String getAddress() { return address; }

    public String getDescription() { return description; }

    public String getSite() { return site; }

    public String getMail() { return mail; }

    public String getPhone() { return phone; }

    public int getDeliveryZone() { return deliveryZone; }
}

