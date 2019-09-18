package ar.edu.unq.desapp.groupj.backend.model;

import java.util.ArrayList;

public class ViandasSystem {

    private static ViandasSystem miViandasSystem;

    ArrayList<Client> clients = new ArrayList<Client>();


    private ViandasSystem() {};

    public static ViandasSystem getViandasSystem() {
        if (miViandasSystem == null) { miViandasSystem = new ViandasSystem(); }
        return miViandasSystem;
    }

    public void registerClient(Client aClient) {

        this.clients.add(aClient);


    }


    public ArrayList<Client> getClients() { return clients; }

    public int chargeCreditToClient(int credit, Client aClient) { return aClient.chargeCredit(credit); }

    public void clientPostService(Client aClient, Service aService) { aClient.postService(aService); }

    public void addMenuToService(Menu aMenu, Service aService) { aService.addMenu(aMenu); }
}
