package ar.edu.unq.desapp.groupj.backend.model;

public class ClientBuilder {

    private String firstName = "no firstName";
    private String lastName = "no lastName";
    private String mail = "no@mail.com";
    private String phone = "0000-00000000";
    private String city = "no city";
    private String address = "no address";
    private Menu menu;
    private int credit = 0;

    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }

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

    public ClientBuilder withMenu(Menu aMenu) {
        menu = aMenu;
        return this;
    }

    //GETTERS
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMail() { return mail; }
    public String getPhone() { return phone; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public int getCredit() { return credit; }

    //SETTERS
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setMail(String mail) { this.mail = mail; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setCity(String city) { this.city = city; }
    public void setAddress(String address) { this.address = address; }
    public void setCredit(int credit) { this.credit = credit; }
}