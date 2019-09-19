package ar.edu.unq.desapp.groupj.backend.model;

import java.util.ArrayList;
import java.util.List;

public class ViandasSystem {

    private static ViandasSystem miViandasSystem;

    List<User> users = new ArrayList<User>();


    private ViandasSystem() {};

    public static ViandasSystem getViandasSystem() {
        if (miViandasSystem == null) { miViandasSystem = new ViandasSystem(); }
        return miViandasSystem;
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

    public List<Menu> findMenuByName(String name) {
        List<Menu> results = new ArrayList<Menu>();

        this.getUsers().forEach( user -> results.addAll( user.findMenuByName(name) ));

        return results;
    }
}