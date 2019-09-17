package ar.edu.unq.desapp.groupj.backend.model;

import java.util.ArrayList;

public class ProviderBuilder extends ClientBuilder{

    private ArrayList<Service> services = new ArrayList<Service>();
    private ArrayList<Menu> menus = new ArrayList<Menu>();

    public static ProviderBuilder aProvider() {
        return new ProviderBuilder();
    }

    public Provider build() {
        return new Provider(this.getFirstName(), this.getLastName(), this.getMail(), this.getPhone(), this.getCity(), this.getAddress(), this.getCredit(), services, menus);
    }

    public ProviderBuilder withName(final String aFirstName, final String aLastName) {
        this.setFirstName(aFirstName);
        this.setLastName(aLastName);
        return this;
    }

    public ProviderBuilder withCredit(int initialCredit) {
        this.setCredit(initialCredit);
        return this;
    }

    public ProviderBuilder withMail(final String aMail) {
        this.setMail(aMail);
        return this;
    }

    public ProviderBuilder withPhone(final String aPhone) {
        this.setPhone(aPhone);
        return this;
    }

    public ProviderBuilder withAddress(final String anAddress) {
        this.setAddress(anAddress);
        return this;
    }

    public ProviderBuilder withCity(String aCity) {
        this.setCity(aCity);
        return this;
    }

    public ProviderBuilder withMenu(Menu aMenu) {
        this.menus.add(aMenu);
        return this;
    }

    public ProviderBuilder withService(Service aService) {
        this.services.add(aService);
        return this;
    }

}
