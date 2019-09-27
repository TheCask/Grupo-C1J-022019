package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class ServiceTest {

    @Test
    public void addSiteToService() {
        String site = "vegan_meals_now.com";
        Service aService = ServiceBuilder.aService().build();

        aService.addSite(site);

        assertEquals(site, aService.getSite());
    }

    @Test
    public void setValidMail() {
        String validMail = "myName@myDomain.com";
        Service aService = ServiceBuilder.aService().build();

        aService.setMail(validMail);

        assertEquals(validMail, aService.getMail());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidFormatMailWithoutAt() {
        String mailWithoutAt = "mailNameDomain.com";
        Service aService = ServiceBuilder.aService().build();

        aService.setMail(mailWithoutAt);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidFormatMailWithoutDomainDot() {
        String mailWithoutDot = "mail.Name@Domaincom";
        Service aService = ServiceBuilder.aService().build();

        aService.setMail(mailWithoutDot);
    }

    @Test
    public void setValidDescription() {
        String description = "A valid description has to be from 30 to 200 chars long";
        Service aService = ServiceBuilder.aService().build();

        aService.setDescription(description);

        assertEquals(description, aService.getDescription());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidShortLengthDescription() {
        String shortDescription = "short description";
        Service aService = ServiceBuilder.aService().build();

        aService.setDescription(shortDescription);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidLongLengthDescription() {
        String longDescription = "A                                                                              " +
                "                                                                                                " +
                "                                                                                                " +
                "                                                                                long description";
        Service aService = ServiceBuilder.aService().build();

        aService.setDescription(longDescription);
    }

    @Test
    public void addMenu() {
        Menu menuMock = mock(Menu.class);

        Service aService = ServiceBuilder.aService().build();
        assertEquals(0, aService.getMenus().size());

        aService.addMenu(menuMock);
        assertEquals(1, aService.getMenus().size());
        assertTrue(aService.getMenus().contains(menuMock));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addMenuToServiceWithMaximumActiveMenus() {
        Menu menuMock = mock(Menu.class);
        when(menuMock.active()).thenReturn(true);
        Service aService = ServiceBuilder.aService().build();
        for (int i = 1; i <= 20; i++) { aService.addMenu(menuMock); }
        assertEquals(20, aService.getMenus().size());

        aService.addMenu(menuMock);
    }

    @Test
    public void removeMenuFromService() {
        Menu menuMock = mock(Menu.class);

        Service aService = ServiceBuilder.aService().build();
        aService.addMenu(menuMock);
        assertEquals(1, aService.getMenus().size());

        aService.deleteMenu(menuMock);
        assertFalse(aService.getMenus().contains(menuMock));
        assertEquals(0, aService.getMenus().size());
    }

    @Test
    public void updateMenuFromService() {
        Menu menuMock1 = mock(Menu.class);
        Menu menuMock2 = mock(Menu.class);

        Service aService = ServiceBuilder.aService().build();
        aService.addMenu(menuMock1);
        assertEquals(1, aService.getMenus().size());
        assertTrue(aService.getMenus().contains(menuMock1));

        aService.updateMenu(menuMock1, menuMock2);
        assertFalse(aService.getMenus().contains(menuMock1));
        assertTrue(aService.getMenus().contains(menuMock2));
        assertEquals(1, aService.getMenus().size());
    }

    @Test
    public void getMenusByName() {
        Service aService = ServiceBuilder.aService().build();
        Menu aMenuPizza = mock(Menu.class);
        Menu aMenuPasta = mock(Menu.class);

        when(aMenuPizza.getName()).thenReturn("Si te gusta la Pizza!!");
        when(aMenuPasta.getName()).thenReturn("Pasta base");

        aService.addMenu(aMenuPizza);
        aService.addMenu(aMenuPasta);

        List<Menu> foundMenus = aService.getMenusByName("pizza");

        assertEquals(1, foundMenus.size() );
        assertTrue( foundMenus.contains(aMenuPizza) );
    }

    @Test
    public void getMenusByCategory() {
        Service aService = ServiceBuilder.aService().build();
        Menu aMenuPizza = mock(Menu.class);
        Menu aMenuPasta = mock(Menu.class);

        when(aMenuPizza.getCategory()).thenReturn(MenuCategory.Pizza);
        when(aMenuPasta.getCategory()).thenReturn(MenuCategory.Sushi);

        aService.addMenu(aMenuPizza);
        aService.addMenu(aMenuPasta);

        List<Menu> foundMenus = aService.getMenusByCategory(MenuCategory.Pizza);

        assertEquals(1, foundMenus.size() );
        assertTrue( foundMenus.contains(aMenuPizza) );
    }

    @Test
    public void getMenusByCity() {
        Service aService = ServiceBuilder.aService().withCity("Bernal").build();
        Menu aMenuPizza = mock(Menu.class);
        Menu aMenuPasta = mock(Menu.class);

        aService.addMenu(aMenuPizza);
        aService.addMenu(aMenuPasta);

        List<Menu> foundMenus = aService.getMenusByCity("Bernal");

        assertEquals(2, foundMenus.size() );
        assertTrue( foundMenus.contains(aMenuPizza) );
        assertTrue( foundMenus.contains(aMenuPasta) );
    }

    @Test
    public void placeClientOrder() {
        Service aService = ServiceBuilder.aService().build();
        User aClient = mock(User.class);
        Menu aMenu = mock(Menu.class);
        LocalDate deliveryDate = LocalDate.now();
        DeliveryType deliveryType = DeliveryType.DeliverToAddress;
        int amount = 10;

        aService.addMenu(aMenu);

        aService.placeClientOrder(aClient,aMenu,deliveryDate,deliveryType,amount);

        verify(aMenu, Mockito.times(1)).placeClientOrder(aClient,deliveryDate,deliveryType,amount);
    }
}