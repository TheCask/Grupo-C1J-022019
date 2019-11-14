package ar.edu.unq.desapp.groupj.backend.model;

public class FoodServiceBuilder {

    private User provider;
    private String name = "no name";
    private String city = "no city";
    private String mail = "no@mail.com";
    private String phone = "0000-00000000";
    private String address = "no address";
    private String description = "no description";
    private int deliveryZone = 0;

    public static FoodServiceBuilder aFoodService() {
        return new FoodServiceBuilder();
    }

    public FoodService build() {
        return new FoodService(provider, name, city, address, description, mail, phone, deliveryZone);
    }

    public FoodServiceBuilder withProvider(final User aProvider) {
        provider = aProvider;
        return this;
    }

    public FoodServiceBuilder withName(final String aName) {
        name = aName;
        return this;
    }

    public FoodServiceBuilder withCity(final String aCity) {
        city = aCity;
        return this;
    }

    public FoodServiceBuilder withMail(final String aMail) {
        mail = aMail;
        return this;
    }

    public FoodServiceBuilder withPhone(final String aPhone) {
        phone = aPhone;
        return this;
    }

    public FoodServiceBuilder withAddress(final String anAddress) {
        address = anAddress;
        return this;
    }

    public FoodServiceBuilder withDescription(final String aDescription) {
        description = aDescription;
        return this;
    }

    public FoodServiceBuilder withDeliveryZone(int aDeliveryZone) {
        deliveryZone = aDeliveryZone;
        return this;
    }
}
