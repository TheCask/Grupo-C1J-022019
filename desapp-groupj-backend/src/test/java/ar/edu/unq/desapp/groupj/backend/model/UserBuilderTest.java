package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class UserBuilderTest {

    @Test
    public void testCreateUserWithName() {
        String firstName = "Alfonso";
        String lastName = "Prat Gay";

        User aUser = UserBuilder.aUser().withName(firstName, lastName).build();

        assertEquals(firstName, aUser.getFirstName());
        assertEquals(lastName, aUser.getLastName());
    }

    @Test
    public void testCreateUserWithCredit() {
        int credit = 3000;

        User aUser = UserBuilder.aUser().withCredit(credit).build();

        assertEquals(credit, aUser.getCredit());
    }

    @Test
    public void testCreateUserWithMail() {
        String mail = "viendas_ya@gmail.com";

        User aUser = UserBuilder.aUser().withMail(mail).build();

        assertEquals(mail, aUser.getMail());
    }

    @Test
    public void testCreateUserWithPhone() {
        String phone = "011-43755600";

        User aUser = UserBuilder.aUser().withPhone(phone).build();

        assertEquals(phone, aUser.getPhone());
    }

    @Test
    public void testCreateUserWithCity() {
        String city = "Bernal";

        User aUser = UserBuilder.aUser().withCity(city).build();

        assertEquals(city, aUser.getCity());
    }

    @Test
    public void testCreateUserWithAddress() {
        String address = "Roque Saenz Peña, 180";

        User aUser = UserBuilder.aUser().withAddress(address).build();

        assertEquals(address, aUser.getAddress());
    }
}
