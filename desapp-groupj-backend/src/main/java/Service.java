import java.util.Date;

public class Service {
    String name;
    String city;
    String address;
    String description;
    String site;
    String mail;
    String phone;
    int deliveryZone;

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
}
