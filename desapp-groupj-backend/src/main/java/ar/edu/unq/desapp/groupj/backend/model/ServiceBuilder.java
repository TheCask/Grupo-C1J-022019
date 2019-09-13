package ar.edu.unq.desapp.groupj.backend.model;

public class ServiceBuilder {

    private String name = "no name";
    private String city = "no city";
    private String mail = "no@mail.com";
    private String phone = "0000-00000000";
    private String address = "no address";
    private String description = "no description";
    private int deliveryZone = 0;

    public static ServiceBuilder aService() {
        return new ServiceBuilder();
    }

    public Service build() {
        return new Service(name, city, address, description, mail, phone, deliveryZone);
    }

    public ServiceBuilder withName(final String aName) {
        name = aName;
        return this;
    }

    public ServiceBuilder withCity(final String aCity) {
        city = aCity;
        return this;
    }

    public ServiceBuilder withMail(final String aMail) {
        mail = aMail;
        return this;
    }

    public ServiceBuilder withPhone(final String aPhone) {
        phone = aPhone;
        return this;
    }

    public ServiceBuilder withAddress(final String anAddress) {
        address = anAddress;
        return this;
    }

    public ServiceBuilder withDescription(final String aDescription) {
        description = aDescription;
        return this;
    }

    public ServiceBuilder withDeliveryZone(int aDeliveryZone) {
        deliveryZone = aDeliveryZone;
        return this;
    }
}
