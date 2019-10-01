package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class ViandasSystemTest {

    private ViandasSystem system;

    @Before
    public void setUp() {
        system = ViandasSystem.getViandasSystem();
        system.getUsers().clear();
    }

    @Test
    public void UserRegistration() {
        User aUser = mock(User.class);

        system.registerUser(aUser);

        assertTrue(system.getUsers().contains(aUser));
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryToRegisterAUserAlreadyRegisteredWithSameMailFails() {
        String aMail = "firstName_lastName@domain.com";
        User aUser = UserBuilder.aUser().withMail(aMail).build();
        User anotherUser = UserBuilder.aUser().withMail(aMail).build();

        system.registerUser(aUser);

        system.registerUser(anotherUser);
    }

    @Test
    public void chargeCreditToUser() {
        int credit = 555;
        User aUser = mock(User.class);
        when(aUser.chargeCredit(anyInt())).thenReturn(anyInt());

        system.chargeCreditToUser(credit, aUser);

        verify(aUser, Mockito.times(1)).chargeCredit(anyInt());
    }

    @Test
    public void withdrawCreditFromUser() {
        int creditToWithdraw = 555;
        int newCredit = 125;
        User aUser = mock(User.class);
        when(aUser.withdrawCredit(anyInt())).thenReturn(newCredit);

        int expectedCredit = system.withdrawCreditFromUser(creditToWithdraw, aUser);

        verify(aUser, Mockito.times(1)).withdrawCredit(creditToWithdraw);
        assertEquals(newCredit, expectedCredit);
    }

    @Test
    public void uploadService() {
        Service aService = mock(Service.class);
        User aUser = mock(User.class);

        system.userPostService(aUser, aService);

        verify(aUser, Mockito.times(1)).postService(aService);
    }

    @Test
    public void addMenuToService() {
        Service aService = mock(Service.class);
        Menu aMenu = mock(Menu.class);

        system.addMenuToService(aMenu, aService);

        verify(aService, Mockito.times(1)).addMenu(aMenu);
    }

    @Test
    public void getMenusByName() {
        User aUser = UserBuilder.aUser().withName("Pocho","La Pantera").withMail("a@a").build();
        Service aService = ServiceBuilder.aService().withName("PochoFood").build();
        Menu aMenuVeggie1 = mock(Menu.class);
        Menu aMenuMeat = mock(Menu.class);
        Menu aMenuVeggie2 = mock(Menu.class);

        when(aMenuVeggie1.getName()).thenReturn("Green veggie");
        when(aMenuMeat.getName()).thenReturn("Crazy beef");
        when(aMenuVeggie2.getName()).thenReturn("Veggie Cow");

        system.registerUser(aUser);
        system.userPostService(aUser, aService);
        system.addMenuToService(aMenuVeggie1,aService);
        system.addMenuToService(aMenuMeat,aService);
        system.addMenuToService(aMenuVeggie2,aService);

        List<Menu> foundMenus = system.getMenusByName("veggie");

        assertEquals( 2, foundMenus.size() );
        assertTrue( foundMenus.contains(aMenuVeggie1) );
        assertTrue( foundMenus.contains(aMenuVeggie2) );
    }

    @Test
    public void getMenusByCategory() {
        User aUser = UserBuilder.aUser().withName("Ricky","Maravilla").withMail("b@b").build();
        Service aService = ServiceBuilder.aService().withName("PochoFood").build();
        Menu aMenuVeggie1 = mock(Menu.class);
        Menu aMenuMeat = mock(Menu.class);
        Menu aMenuVeggie2 = mock(Menu.class);

        when(aMenuVeggie1.getCategory()).thenReturn(MenuCategory.Vegano);
        when(aMenuMeat.getCategory()).thenReturn(MenuCategory.Hamburguesa);
        when(aMenuVeggie2.getCategory()).thenReturn(MenuCategory.Vegano);

        system.registerUser(aUser);
        system.userPostService(aUser, aService);
        system.addMenuToService(aMenuVeggie1,aService);
        system.addMenuToService(aMenuMeat,aService);
        system.addMenuToService(aMenuVeggie2,aService);

        List<Menu> foundMenus = system.getMenusByCategory(MenuCategory.Vegano);

        assertEquals( 2, foundMenus.size() );
        assertTrue( foundMenus.contains(aMenuVeggie1) );
        assertTrue( foundMenus.contains(aMenuVeggie2) );
    }

    @Test
    public void getMenusByCity() {
        String city = "Bernal";
        User aUser = UserBuilder.aUser().withName("Miguel","Conejito Alejandro").withMail("c@c").build();
        Service aService = ServiceBuilder.aService().withName("PochoFood").withCity(city).build();
        Menu aMenuVeggie = mock(Menu.class);
        Menu aMenuMeat = mock(Menu.class);

        system.registerUser(aUser);
        system.userPostService(aUser, aService);
        system.addMenuToService(aMenuVeggie,aService);
        system.addMenuToService(aMenuMeat,aService);

        List<Menu> foundMenus = system.getMenusByCity(city);

        assertEquals( 2, foundMenus.size() );
        assertTrue( foundMenus.contains(aMenuVeggie) );
        assertTrue( foundMenus.contains(aMenuMeat) );
    }

    @Test
    public void placeClientOrder() {
        User aProvider = mock(User.class);
        User aClient = mock(User.class);
        Service aService = mock(Service.class);
        Menu aMenu = mock(Menu.class);
        LocalDate deliveryDate = LocalDate.now();
        DeliveryType deliveryType = DeliveryType.DeliverToAddress;
        int amount = 10;
        system.registerUser(aProvider);
        system.registerUser(aClient);

        system.placeClientOrder(aClient,aProvider,aService,aMenu,deliveryDate,deliveryType,amount);

        verify(aProvider, Mockito.times(1)).placeClientOrder(aClient,aService,aMenu,deliveryDate,deliveryType,amount);
    }

    @Test
    public void clientRateServiceWithValidValue() {
        int aValue = 3;
        User aClient = mock(User.class);
        Menu aMenu = mock(Menu.class);

        Rate aRate = system.clientRatesMenu(aClient, aMenu, aValue);

        assertEquals(aRate.getValue(), aValue);
        verify(aMenu, Mockito.times(1)).addRate(aRate);
    }

    @Test (expected = IllegalArgumentException.class)
    public void clientTryToRateServiceWithInvalidNegativeValue() {
        int aValue = -3;
        User aClient = mock(User.class);
        Menu aMenu = mock(Menu.class);

        system.clientRatesMenu(aClient, aMenu, aValue);
    }

    @Test (expected = IllegalArgumentException.class)
    public void clientTryToRateServiceWithInvalidLowValue() {
        int aValue = 0;
        User aClient = mock(User.class);
        Menu aMenu = mock(Menu.class);

        system.clientRatesMenu(aClient, aMenu, aValue);
    }

    @Test (expected = IllegalArgumentException.class)
    public void clientTryToRateServiceWithInvalidHighValue() {
        int aValue = 6;
        User aClient = mock(User.class);
        Menu aMenu = mock(Menu.class);

        system.clientRatesMenu(aClient, aMenu, aValue);
    }

    @Test
    public void newRateBanMenuBecauseLowAverageRate() {

        User aClient = mock(User.class);
        Menu aMenu = mock(Menu.class);
        when(aMenu.getAverageRate()).thenReturn(1);
        when(aMenu.getRateCount()).thenReturn(21);
        when(aMenu.isBanned()).thenCallRealMethod();
        int aValue = 1;

        system.clientRatesMenu(aClient, aMenu, aValue);

        verify(aMenu, Mockito.times(1)).isBanned();
        verify(aMenu, Mockito.times(1)).cancelAllOrders();
    }

    @Test
    public void newBannedMenuBanProvider() {
        Menu aMenu = mock(Menu.class);
        when(aMenu.isBanned()).thenReturn(true);

        User aProvider = UserBuilder.aUser().build();
        Service aService = ServiceBuilder.aService().build();

        system.userPostService(aProvider, aService);
        for (int i = 1; i <= 9; i++) { system.addMenuToService(aMenu, aService); }

        assertEquals(aProvider.getMenus().size(), 9);
        assertFalse(aProvider.isBanned());

        system.addMenuToService(aMenu, aService);

        assertTrue(aProvider.isBanned());
    }

    @Test
    public void clientHasMenusToRate() {
        int value = 4;
        User aClient = UserBuilder.aUser().withMail("toti@folni.com").withCredit(100000).build();
        User aProvider = UserBuilder.aUser().build();
        Service aService = ServiceBuilder.aService().build();
        Menu aMenu = Menu.Builder.aMenu().
                withName("Fugazetta Rellena").
                withDescription("Alta fugazzeta papuuu").
                withCategory(MenuCategory.Pizza).
                withDeliveryValue(20.0).
                withAvailableFrom(LocalDate.now()).
                withAvailableTo(LocalDate.now()).
                withDeliveryShifts(null).
                withAverageDeliveryTime(1).
                withPrice(100.0).
                withMinimumAmount1(13).
                withMinimumAmount1Price(78.0).
                withMinimumAmount2(50).
                withMinimumAmount2Price(63.0).
                withMaximumDailySales(50).
                build();

        LocalDate aDeliveryDate = LocalDate.now().plusDays(3);
        DeliveryType aDeliveryType = DeliveryType.DeliverToAddress;

        system.registerUser(aClient);
        system.registerUser(aProvider);
        system.userPostService(aProvider, aService);
        system.addMenuToService(aMenu, aService);

        system.placeClientOrder(aClient, aProvider, aService, aMenu, aDeliveryDate, aDeliveryType, value);

        assertEquals(0, system.allRatesFromClient(aClient).size());
        assertEquals(1, system.allOrderDetailsFromClient(aClient).size());
        assertTrue(system.hasMenusToRate(aClient));

        system.clientRatesMenu(aClient, aMenu, value);

        assertEquals(1, system.allRatesFromClient(aClient).size());
        assertEquals(1, system.allOrderDetailsFromClient(aClient).size());
        assertFalse(system.hasMenusToRate(aClient));
    }

    @Test
    public void confirmOrders() {

        List<Menu> menuListOfSizeTwo = mock(List.class);
        when(menuListOfSizeTwo.size()).thenReturn(2);
        User aProvider = mock(User.class);
        when(aProvider.getMenus()).thenReturn(menuListOfSizeTwo);
        User otherProvider = mock(User.class);
        when(otherProvider.getMenus()).thenReturn(menuListOfSizeTwo);
        system.registerUser(aProvider);
        system.registerUser(otherProvider);

        system.confirmOrders();

        verify(aProvider, Mockito.times(1)).confirmOrders();
        verify(otherProvider, Mockito.times(1)).confirmOrders();

    }
 }