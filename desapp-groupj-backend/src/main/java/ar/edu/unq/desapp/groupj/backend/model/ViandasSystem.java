package ar.edu.unq.desapp.groupj.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class ViandasSystem {

    private static ViandasSystem myViandasSystem;

    private Set<User> users = new HashSet<>();

    private ViandasSystem() {}

    public static ViandasSystem getViandasSystem() {
        if (myViandasSystem == null) { myViandasSystem = new ViandasSystem(); }
        return myViandasSystem;
    }

    public void registerUser(User aUser) {
        if (!this.containsUser(aUser)) { this.users.add(aUser); }
        else { throw new IllegalArgumentException("User already registered"); }
    }

    public boolean containsUser(User aUser) {
        return this.users.stream().anyMatch(user -> user.equals(aUser));
    }

    public Set<User> getUsers() { return users; }

    public Double chargeCreditToUser(Double credit, User aUser) { return aUser.chargeCredit(credit); }

    public void userPostFoodService(User aUser, FoodService aFoodService) { aUser.postFoodService(aFoodService); }

    public void addMenuToFoodService(Menu aMenu, FoodService aFoodService) {
        aFoodService.addMenu(aMenu);
        aMenu.setFoodService(aFoodService);
    }

    public Double withdrawCreditFromUser(Double creditToWithdraw, User aUser) {
        return aUser.withdrawCredit(creditToWithdraw);
    }

    public List<Menu> getMenusByName(String name) {
        List<Menu> results = new ArrayList<>();

        this.getUsers().forEach( user -> results.addAll( user.getMenusByName(name) ));

        return results;
    }

    public List<Menu> getMenusByCategory(MenuCategory category) {
        List<Menu> results = new ArrayList<>();

        this.getUsers().forEach( user -> results.addAll( user.getMenusByCategory(category) ));

        return results;
    }

    public List<Menu> getMenusByCity(String city) {
        List<Menu> results = new ArrayList<>();

        this.getUsers().forEach( user -> results.addAll( user.getMenusByCity(city) ));

        return results;
    }

    public void placeClientOrder( User aClient, User aProvider,
                                  FoodService aFoodService, Menu aMenu,
                                  LocalDate deliveryDate, LocalTime deliveryTime,
                                  DeliveryType deliveryType, int amount) {
        if( !this.getUsers().contains(aProvider) || !this.getUsers().contains(aClient) )
            throw new IllegalArgumentException("Usuario no registrado en el sistema.");

         aProvider.placeClientOrder(aClient, aFoodService,aMenu,deliveryDate,deliveryTime,deliveryType,amount);
    }

    public Rate clientRatesMenu(User aClient, Menu aMenu, int aValue) {
        Rate aRate = new Rate(aClient, aValue);
        aMenu.addRate(aRate);
        if (aMenu.banned()) { this.banMenu(aMenu); }
        return aRate;
    }

    private void banMenu(Menu aMenu){
        Optional<User> providerToNotify = this.getUsers().stream().
                filter(user -> user.getMenus().contains(aMenu)).
                findFirst();
        if (providerToNotify.isPresent()) { providerToNotify.get().notifyBan(aMenu); }
        aMenu.cancelAllOrders();
    }

    public boolean hasMenusToRate(User aClient) {
        return this.allRatesFromClient(aClient).size() != this.allOrderDetailsFromClient(aClient).size();
    }

    public List<User> providers() {
        return this.users.stream().
                filter(provider -> provider.getMenus().size() > 0).collect(Collectors.toList());
    }

    public List<Menu> allMenus() {
        return this.providers().stream().
                flatMap(provider -> provider.getMenus().stream()).collect(Collectors.toList());
    }

    public List<Rate> allRatesFromClient(User aClient) {
        return this.allMenus().stream().
                flatMap(menu -> menu.getRates().stream().
                filter(rate -> rate.getUser().equals(aClient))).collect(Collectors.toList());
    }

    public List<OrderDetail> allOrderDetailsFromClient(User aClient) {
        return this.allMenus().stream().
                flatMap(menu -> menu.getOrders().stream().
                flatMap(order -> order.getDetails().stream())).
                filter(detail -> detail.getUser().equals(aClient)).collect(Collectors.toList());
    }

    public void confirmOrders() {
        this.providers().forEach(provider -> provider.confirmOrders());
    }
}