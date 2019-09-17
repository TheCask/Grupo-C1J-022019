package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClientBuilderTest {

    @Test
    public void testCreateClientWithName() {
        String firstName = "Alfonso";
        String lastName = "Prat Gay";

        Client aClient = ClientBuilder.aClient().withName(firstName, lastName).build();

        assertEquals(firstName, aClient.getFirstName());
        assertEquals(lastName, aClient.getLastName());
    }

    @Test
    public void testCreateClientWithCredit() {
        int credit = 3000;

        Client aClient = ClientBuilder.aClient().withCredit(credit).build();

        assertEquals(credit, aClient.getCredit());
    }

    @Test
    public void testCreateClientWithMail() {
        String mail = "viendas_ya@gmail.com";

        Client aClient = ClientBuilder.aClient().withMail(mail).build();

        assertEquals(mail, aClient.getMail());
    }

    @Test
    public void testCreateClientWithPhone() {
        String phone = "011-43755600";

        Client aClient = ClientBuilder.aClient().withPhone(phone).build();

        assertEquals(phone, aClient.getPhone());
    }

    @Test
    public void testCreateClientWithCity() {
        String city = "Bernal";

        Client aClient = ClientBuilder.aClient().withCity(city).build();

        assertEquals(city, aClient.getCity());
    }

    @Test
    public void testCreateClientWithAddress() {
        String address = "Roque Saenz Peña, 180";

        Client aClient = ClientBuilder.aClient().withAddress(address).build();

        assertEquals(address, aClient.getAddress());
    }
}
