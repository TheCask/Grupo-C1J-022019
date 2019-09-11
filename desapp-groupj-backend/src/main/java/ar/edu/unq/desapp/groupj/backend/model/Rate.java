package ar.edu.unq.desapp.groupj.backend.model;

public class Rate {

    private Client client;
    private int value;

    public Rate(Client client, int value) {
        this.client = client;
        this.value = value;
    }

    public Client getClient(){ return this.client; }
    public int getValue() { return this.value; }
}
