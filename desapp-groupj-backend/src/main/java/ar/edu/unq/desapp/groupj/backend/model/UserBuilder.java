package ar.edu.unq.desapp.groupj.backend.model;

public class UserBuilder {

    private String firstName = "no firstName";
    private String lastName = "no lastName";
    private String mail = "no@mail.com";
    private String phone = "0000-00000000";
    private String city = "no city";
    private String address = "no address";
    private int credit = 0;

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public User build() {
        return new User(firstName, lastName, mail, phone, city, address, credit);
    }

    public UserBuilder withName(final String aFirstName, final String aLastName) {
        firstName = aFirstName;
        lastName = aLastName;
        return this;
    }

    public UserBuilder withCredit(int initialCredit) {
        credit = initialCredit;
        return this;
    }

    public UserBuilder withMail(final String aMail) {
        mail = aMail;
        return this;
    }

    public UserBuilder withPhone(final String aPhone) {
        phone = aPhone;
        return this;
    }

    public UserBuilder withAddress(final String anAddress) {
        address = anAddress;
        return this;
    }

    public UserBuilder withCity(String aCity) {
        city = aCity;
        return this;
    }
}