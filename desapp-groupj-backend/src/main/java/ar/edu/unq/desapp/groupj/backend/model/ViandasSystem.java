package ar.edu.unq.desapp.groupj.backend.model;

import java.awt.*;
import java.util.ArrayList;

public class ViandasSystem {

    private static ViandasSystem miViandasSystem;

    ArrayList<Client> clients = new ArrayList<Client>();
    ArrayList<Order>  orders = new ArrayList<Order>();

    private ViandasSystem() {};

    public static ViandasSystem getViandasSystem() {
        if (miViandasSystem == null) { miViandasSystem = new ViandasSystem(); }
        return miViandasSystem;
    }

    public void registerClient(Client aClient) {

        if (!this.clients.contains(aClient)) { this.clients.add(aClient); }
        else { throw new IllegalArgumentException("Client already registered"); }
    }

    public ArrayList<Client> getClients() { return clients; }

    public int chargeCreditToClient(int credit, Client aClient) { return aClient.chargeCredit(credit); }

    public void clientPostService(Client aClient, Service aService) { aClient.postService(aService); }

    public void addMenuToService(Menu aMenu, Service aService) { aService.addMenu(aMenu); }

    public int withdrawCreditFromClient(int creditToWithdraw, Client aClient) {
        return aClient.withdrawCredit(creditToWithdraw);
    }

    public void clientBuyMenu(Client aClient, Menu aMenu) {
        this.orders.add(Order.Builder.anOrder().build());
    }

    // GETTERS

    public ArrayList<Order> getOrders() { return orders; }
}