package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FoodServiceBuilderTest {
    @Test
    public void testCreateFoodServiceWithName() {
        String name = "Vegan Meals Now";

        FoodService aFoodService = FoodServiceBuilder.aFoodService().withName(name).build();

        assertEquals(name, aFoodService.getName());
    }

    @Test
    public void testCreateFoodServiceWithDeliveryZone() {
        int deliveryZone = 5;

        FoodService aFoodService = FoodServiceBuilder.aFoodService().withDeliveryZone(deliveryZone).build();

        assertEquals(deliveryZone, aFoodService.getDeliveryZone());
    }

    @Test
    public void testCreateFoodServiceWithMail() {
        String mail = "viendas_ya@gmail.com";

        FoodService aFoodService = FoodServiceBuilder.aFoodService().withMail(mail).build();

        assertEquals(mail, aFoodService.getMail());
    }

    @Test
    public void testCreateFoodServiceWithDescription() {
        String description = "We offer vegan rich protein meals for you to enjoy!";

        FoodService aFoodService = FoodServiceBuilder.aFoodService().withDescription(description).build();

        assertEquals(description, aFoodService.getDescription());
    }

    @Test
    public void testCreateFoodServiceWithPhone() {
        String phone = "011-43755600";

        FoodService aFoodService = FoodServiceBuilder.aFoodService().withPhone(phone).build();

        assertEquals(phone, aFoodService.getPhone());
    }

    @Test
    public void testCreateFoodServiceWithCity() {
        String city = "Bernal";

        FoodService aFoodService = FoodServiceBuilder.aFoodService().withCity(city).build();

        assertEquals(city, aFoodService.getCity());
    }

    @Test
    public void testCreateFoodServiceWithAddress() {
        String address = "Roque Saenz Peña, 180";

        FoodService aFoodService = FoodServiceBuilder.aFoodService().withAddress(address).build();

        assertEquals(address, aFoodService.getAddress());
    }
}
