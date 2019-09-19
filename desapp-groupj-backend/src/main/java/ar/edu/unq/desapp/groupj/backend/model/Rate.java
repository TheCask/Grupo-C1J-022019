package ar.edu.unq.desapp.groupj.backend.model;

public class Rate {

    private User user;
    private int value;

    public Rate(User user, int value) {
        this.user = user;
        this.value = value;
    }

    public User getUser(){ return this.user; }
    public int getValue() { return this.value; }
}
