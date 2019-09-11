package ar.edu.unq.desapp.groupj.backend.model;

public class ClientBuilder {
    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }

    private String firstName = "no firstName";
    private String lastName = "no lastName";
    private String mail = "no@mail.com";
    private String phone = "0000-00000000";
    private String city = "no city";
    private String address = "no address";
    private int credit = 0;

    public Client build() {
        return new Client(firstName, lastName, mail, phone, city, address, credit);
    }

    public ClientBuilder withName(final String aFirstName, final String aLastName) {
        firstName = aFirstName;
        lastName = aLastName;
        return this;
    }

    public ClientBuilder withCredit(int initialCredit) {
        credit = initialCredit;
        return this;
    }

    public ClientBuilder withMail(final String aMail) {
        mail = aMail;
        return this;
    }

    public ClientBuilder withPhone(final String aPhone) {
        phone = aPhone;
        return this;
    }

    public ClientBuilder withAddress(final String anAddress) {
        address = anAddress;
        return this;
    }

    public ClientBuilder withCity(String aCity) {
        city = aCity;
        return this;
    }
}