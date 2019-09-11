package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ServiceBuilderTest {
    @Test
    public void testCreateServiceWithName() {
        String name = "Vegan Meals Now";

        Service aService = ServiceBuilder.aService().withName(name).build();

        assertEquals(name, aService.getName());
    }

    @Test
    public void testCreateServiceWithDeliveryZone() {
        int deliveryZone = 5;

        Service aService = ServiceBuilder.aService().withDeliveryZone(deliveryZone).build();

        assertEquals(deliveryZone, aService.getDeliveryZone());
    }

    @Test
    public void testCreateServiceWithMail() {
        String mail = "viendas_ya@gmail.com";

        Service aService = ServiceBuilder.aService().withMail(mail).build();

        assertEquals(mail, aService.getMail());
    }

    @Test
    public void testCreateServiceWithDescription() {
        String description = "We offer vegan rich protein meals for you to enjoy!";

        Service aService = ServiceBuilder.aService().withDescription(description).build();

        assertEquals(description, aService.getDescription());
    }

    @Test
    public void testCreateServiceWithPhone() {
        String phone = "011-43755600";

        Service aService = ServiceBuilder.aService().withPhone(phone).build();

        assertEquals(phone, aService.getPhone());
    }

    @Test
    public void testCreateServiceWithCity() {
        String city = "Bernal";

        Service aService = ServiceBuilder.aService().withCity(city).build();

        assertEquals(city, aService.getCity());
    }

    @Test
    public void testCreateServiceWithAddress() {
        String address = "Roque Saenz Peña, 180";

        Service aService = ServiceBuilder.aService().withAddress(address).build();

        assertEquals(address, aService.getAddress());
    }
}
