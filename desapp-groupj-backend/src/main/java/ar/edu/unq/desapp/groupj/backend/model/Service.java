package ar.edu.unq.desapp.groupj.backend.model;

public class Service {
    private static final int MIN_DESCRIPTION_LENGTH = 30;
    private static final int MAX_DESCRIPTION_LENGTH = 200;

    private String name;
    private String city;
    private String address;
    private String description; //min 30 max 200 chars
    private String site;
    private String mail; //valid mail
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

    // SETTERS

    public void setName(String name) { this.name = name; }

    public void setCity(String city) { this.city = city; }

    public void setAddress(String address) { this.address = address; }

    public void setDescription(String description) {
        Validators.validateStringLength(description,MIN_DESCRIPTION_LENGTH,MAX_DESCRIPTION_LENGTH,"Description");
        this.description = description;
    }

    public void setSite(String site) { this.site = site; }

    public void setMail(String mail) {
        Validators.validateMail(mail, "mail");
        this.mail = mail;
    }

    public void setPhone(String phone) { this.phone = phone; }

    public void setDeliveryZone(int deliveryZone) { this.deliveryZone = deliveryZone; }
}