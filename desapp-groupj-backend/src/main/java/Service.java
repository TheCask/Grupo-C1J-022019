import java.util.Date;

public class Service {
    String name;
    String city;
    String address;
    String description;
    String site;
    String mail;
    String phone;
    int deliveryRadius;

    public Service(String name, String city, String address, String description, String mail, String phone, int deliveryRadius) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.description = description;
        this.mail = mail;
        this.phone = phone;
        this.deliveryRadius = deliveryRadius;
    }
}
