package ar.edu.unq.desapp.groupj.backend.model;

import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="rates")
public class Rate {

    private static final int MIN_RATE = 1;
    private static final int MAX_RATE = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer value;

    public Rate() {}

    public Rate(Menu menu, User user, Integer value) {
        ValidatorsUtils.validateIntValue(value,MIN_RATE,MAX_RATE,"value");
        setMenu(menu);
        setUser(user);
        setValue(value);
    }

    public Integer getId() { return this.id; }
    public void setId( Integer id ) { this.id = id; }

    @JsonBackReference
    public Menu getMenu() { return this.menu; }
    public void setMenu( Menu menu ) { this.menu = menu; }

    public User getUser(){ return this.user; }
    public void setUser( User user) { this.user = user; }

    public Integer getValue() { return this.value; }
    public void setValue( Integer value ) { this.value = value; }

}
