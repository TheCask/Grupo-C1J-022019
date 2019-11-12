package ar.edu.unq.desapp.groupj.backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void userPostService(User aUser, FoodService aFoodService) { aUser.postService(aFoodService); }

    public void addMenuToService(Menu aMenu, FoodService aFoodService) { aFoodService.addMenu(aMenu); }

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

    public void placeClientOrder(User aClient, User aProvider, FoodService aFoodService, Menu aMenu, LocalDate deliveryDate, DeliveryType deliveryType, int amount) {
        if( !this.getUsers().contains(aProvider) || !this.getUsers().contains(aClient) )
            throw new IllegalArgumentException("Usuario no registrado en el sistema.");

         aProvider.placeClientOrder(aClient, aFoodService,aMenu,deliveryDate,deliveryType,amount);
    }

    public Rate clientRatesMenu(User aClient, Menu aMenu, int aValue) {
        Rate aRate = new Rate(aClient, aValue);
        aMenu.addRate(aRate);
        if (aMenu.isBanned()) { this.banMenu(aMenu); }
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