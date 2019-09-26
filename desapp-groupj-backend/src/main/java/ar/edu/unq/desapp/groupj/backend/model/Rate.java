package ar.edu.unq.desapp.groupj.backend.model;

public class Rate {

    private static final int MIN_RATE = 1;
    private static final int MAX_RATE = 5;

    private User user;
    private int value;

    public Rate(User user, int value) {
        ValidatorsUtils.validateIntValue(value,MIN_RATE,MAX_RATE,"value");
        this.user = user;
        this.value = value;
    }

    public User getUser(){ return this.user; }
    public int getValue() { return this.value; }
}
