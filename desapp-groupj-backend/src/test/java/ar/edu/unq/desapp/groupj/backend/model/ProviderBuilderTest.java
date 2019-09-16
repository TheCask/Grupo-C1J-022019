package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProviderBuilderTest {
    @Test
    public void testCreateProviderWithName() {
        String firstName = "Alfonso";
        String lastName = "Prat Gay";

        Provider aProvider = ProviderBuilder.aProvider().withName(firstName, lastName).build();

        assertEquals(firstName, aProvider.getFirstName());
        assertEquals(lastName, aProvider.getLastName());
    }

    @Test
    public void testCreateProviderWithCredit() {
        int credit = 3000;

        Provider aProvider = ProviderBuilder.aProvider().withCredit(credit).build();

        assertEquals(credit, aProvider.getCredit());
    }

    @Test
    public void testCreateProviderWithMail() {
        String mail = "viendas_ya@gmail.com";

        Provider aProvider = ProviderBuilder.aProvider().withMail(mail).build();

        assertEquals(mail, aProvider.getMail());
    }

    @Test
    public void testCreateProviderWithPhone() {
        String phone = "011-43755600";

        Provider aProvider = ProviderBuilder.aProvider().withPhone(phone).build();

        assertEquals(phone, aProvider.getPhone());
    }

    @Test
    public void testCreateProviderWithCity() {
        String city = "Bernal";

        Provider aProvider = ProviderBuilder.aProvider().withCity(city).build();

        assertEquals(city, aProvider.getCity());
    }

    @Test
    public void testCreateProviderWithAddress() {
        String address = "Roque Saenz Peña, 180";

        Provider aProvider = ProviderBuilder.aProvider().withAddress(address).build();

        assertEquals(address, aProvider.getAddress());
    }
}