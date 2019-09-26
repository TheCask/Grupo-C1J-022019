package ar.edu.unq.desapp.groupj.backend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViandasSystem {

    private static ViandasSystem myViandasSystem;

    private List<User> users = new ArrayList<User>();

    private ViandasSystem() {};

    public static ViandasSystem getViandasSystem() {
        if (myViandasSystem == null) { myViandasSystem = new ViandasSystem(); }
        return myViandasSystem;
    }

    public void registerUser(User aUser) {

        if (!this.users.contains(aUser)) { this.users.add(aUser); }
        else { throw new IllegalArgumentException("User already registered"); }
    }

    public List<User> getUsers() { return users; }

    public int chargeCreditToUser(int credit, User aUser) { return aUser.chargeCredit(credit); }

    public void userPostService(User aUser, Service aService) { aUser.postService(aService); }

    public void addMenuToService(Menu aMenu, Service aService) { aService.addMenu(aMenu); }

    public int withdrawCreditFromUser(int creditToWithdraw, User aUser) {
        return aUser.withdrawCredit(creditToWithdraw);
    }

    public List<Menu> getMenusByName(String name) {
        List<Menu> results = new ArrayList<Menu>();

        this.getUsers().forEach( user -> results.addAll( user.getMenusByName(name) ));

        return results;
    }

    public List<Menu> getMenusByCategory(MenuCategory category) {
        List<Menu> results = new ArrayList<Menu>();

        this.getUsers().forEach( user -> results.addAll( user.getMenusByCategory(category) ));

        return results;
    }

    public List<Menu> getMenusByCity(String city) {
        List<Menu> results = new ArrayList<Menu>();

        this.getUsers().forEach( user -> results.addAll( user.getMenusByCity(city) ));

        return results;
    }

    public void placeClientOrder(User aClient, User aProvider, Service aService, Menu aMenu, Date deliveryDate, DeliveryType deliveryType, int amount) {
        if( !this.getUsers().contains(aProvider) || !this.getUsers().contains(aClient) )
            throw new IllegalArgumentException("Usuario no registrado en el sistema.");

        aProvider.placeClientOrder(aClient,aService,aMenu,deliveryDate,deliveryType,amount);
    }
}