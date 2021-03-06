package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class FoodServiceTest {

    @Test
    public void addSiteToFoodService() {
        String site = "vegan_meals_now.com";
        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();

        aFoodService.addSite(site);

        assertEquals(site, aFoodService.getSite());
    }

    @Test
    public void setValidMail() {
        String validMail = "myName@myDomain.com";
        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();

        aFoodService.setMail(validMail);

        assertEquals(validMail, aFoodService.getMail());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidFormatMailWithoutAt() {
        String mailWithoutAt = "mailNameDomain.com";
        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();

        aFoodService.setMail(mailWithoutAt);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidFormatMailWithoutDomainDot() {
        String mailWithoutDot = "mail.Name@Domaincom";
        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();

        aFoodService.setMail(mailWithoutDot);
    }

    @Test
    public void setValidDescription() {
        String description = "A valid description has to be from 30 to 200 chars long";
        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();

        aFoodService.setDescription(description);

        assertEquals(description, aFoodService.getDescription());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidShortLengthDescription() {
        String shortDescription = "short description";
        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();

        aFoodService.setDescription(shortDescription);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidLongLengthDescription() {
        String longDescription = "A                                                                              " +
                "                                                                                                " +
                "                                                                                                " +
                "                                                                                long description";
        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();

        aFoodService.setDescription(longDescription);
    }

    @Test
    public void addMenu() {
        Menu menuMock = mock(Menu.class);

        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();
        assertEquals(0, aFoodService.getMenus().size());

        aFoodService.addMenu(menuMock);
        assertEquals(1, aFoodService.getMenus().size());
        assertTrue(aFoodService.getMenus().contains(menuMock));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addMenuToFoodServiceWithMaximumActiveMenus() {
        Menu menuMock = mock(Menu.class);
        when(menuMock.active()).thenReturn(true);
        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();
        for (int i = 1; i <= 20; i++) {
            Menu activeMenu = Menu.Builder.aMenu().
                    withName("Menu").
                    withDescription("A Valid description of a menu").
                    withAvailableFrom(LocalDate.now().minusDays(1)).
                    withAvailableTo(LocalDate.now().plusDays(1)).
                    withPrice(90.0).
                    withDeliveryValue(10.0).
                    withMinimumAmount1(13).
                    withMinimumAmount1Price(78.0).
                    withMaximumDailySales(50).
                    withMinimumAmount2(50).
                    withMinimumAmount2Price(70.0).
                    build();
            aFoodService.addMenu(activeMenu);
        }

        assertEquals(20, aFoodService.getMenus().size());

        aFoodService.addMenu(mock(Menu.class));
    }

    @Test
    public void removeMenuFromFoodService() {
        Menu menuMock = mock(Menu.class);

        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();
        aFoodService.addMenu(menuMock);
        assertEquals(1, aFoodService.getMenus().size());

        aFoodService.deleteMenu(menuMock);
        assertFalse(aFoodService.getMenus().contains(menuMock));
        assertEquals(0, aFoodService.getMenus().size());
    }

    @Test
    public void updateMenuFromFoodService() {
        Menu menuMock1 = mock(Menu.class);
        Menu menuMock2 = mock(Menu.class);

        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();
        aFoodService.addMenu(menuMock1);
        assertEquals(1, aFoodService.getMenus().size());
        assertTrue(aFoodService.getMenus().contains(menuMock1));

        aFoodService.updateMenu(menuMock1, menuMock2);
        assertFalse(aFoodService.getMenus().contains(menuMock1));
        assertTrue(aFoodService.getMenus().contains(menuMock2));
        assertEquals(1, aFoodService.getMenus().size());
    }

    @Test
    public void getMenusByName() {
        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();
        Menu aMenuPizza = mock(Menu.class);
        Menu aMenuPasta = mock(Menu.class);

        when(aMenuPizza.getName()).thenReturn("Si te gusta la Pizza!!");
        when(aMenuPasta.getName()).thenReturn("Pasta base");

        aFoodService.addMenu(aMenuPizza);
        aFoodService.addMenu(aMenuPasta);

        List<Menu> foundMenus = aFoodService.getMenusByName("pizza");

        assertEquals(1, foundMenus.size() );
        assertTrue( foundMenus.contains(aMenuPizza) );
    }

    @Test
    public void getMenusByCategory() {
        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();
        Menu aMenuPizza = mock(Menu.class);
        Menu aMenuPasta = mock(Menu.class);

        when(aMenuPizza.getCategory()).thenReturn(MenuCategory.Pizza);
        when(aMenuPasta.getCategory()).thenReturn(MenuCategory.Sushi);

        aFoodService.addMenu(aMenuPizza);
        aFoodService.addMenu(aMenuPasta);

        List<Menu> foundMenus = aFoodService.getMenusByCategory(MenuCategory.Pizza);

        assertEquals(1, foundMenus.size() );
        assertTrue( foundMenus.contains(aMenuPizza) );
    }

    @Test
    public void getMenusByCity() {
        FoodService aFoodService = FoodServiceBuilder.aFoodService().withCity("Bernal").build();
        Menu aMenuPizza = mock(Menu.class);
        Menu aMenuPasta = mock(Menu.class);

        aFoodService.addMenu(aMenuPizza);
        aFoodService.addMenu(aMenuPasta);

        Set<Menu> foundMenus = aFoodService.getMenusByCity("Bernal");

        assertEquals(2, foundMenus.size() );
        assertTrue( foundMenus.contains(aMenuPizza) );
        assertTrue( foundMenus.contains(aMenuPasta) );
    }

    @Test
    public void placeClientOrder() {
        User aProvider = mock(User.class);
        FoodService aFoodService = FoodServiceBuilder.aFoodService().withProvider(aProvider).build();

        User aClient = mock(User.class);
        Menu aMenu = mock(Menu.class);
        when(aMenu.getFoodService()).thenReturn(aFoodService);

        LocalDate deliveryDate = LocalDate.now();
        LocalTime deliveryTime = LocalTime.now();
        DeliveryType deliveryType = DeliveryType.DeliverToAddress;
        int amount = 10;

        aFoodService.addMenu(aMenu);

        aFoodService.placeClientOrder(aClient,aMenu,deliveryDate,deliveryTime,deliveryType,amount);

        verify(aMenu, Mockito.times(1)).placeClientOrder(aClient,deliveryDate,deliveryTime,deliveryType,amount);
    }

    @Test
    public void confirmOrders() {

        FoodService aFoodService = FoodServiceBuilder.aFoodService().build();

        Menu aMenu = mock(Menu.class);
        Menu otherMenu = mock(Menu.class);

        aFoodService.addMenu(aMenu);
        aFoodService.addMenu(otherMenu);

        aFoodService.confirmOrders();

        verify(aMenu, Mockito.times(1)).confirmOrders();
        verify(otherMenu, Mockito.times(1)).confirmOrders();
    }
}