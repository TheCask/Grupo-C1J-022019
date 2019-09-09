public class Client {
    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private String city;
    private String address;
    private int credit;

    public Client(String firstName, String lastName, String mail, String phone, String city, String address, int credit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.city = city;
        this.address = address;
        this.credit = credit;
    }

    public int chargeCredit(int credit) { return this.credit += credit; }

    public int getCredit() { return this.credit; }

    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName; }
}
