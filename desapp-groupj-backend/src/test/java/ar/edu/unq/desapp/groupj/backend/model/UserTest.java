package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {
    @Test
    public void chargeCreditToAccount5000() {
        int credit = 5000;
        User aUser = UserBuilder.aUser().withCredit(0).build();

        aUser.chargeCredit(credit);

        assertEquals(credit, aUser.getCredit());
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryToChargeNegativeCreditToAccount() {
        int negativeCredit = -5000;
        User aUser = UserBuilder.aUser().withCredit(0).build();

        aUser.chargeCredit(negativeCredit);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryToChargeZeroCreditToAccount() {
        int zeroCredit = 0;
        User aUser = UserBuilder.aUser().withCredit(0).build();

        aUser.chargeCredit(zeroCredit);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryToWithdrawCreditFromEmptyAccount() {
        int credit = 5000;
        User aUser = UserBuilder.aUser().withCredit(0).build();

        aUser.withdrawCredit(credit);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryToWithdrawNegativeCreditFromAccount() {
        int negativeCredit = -5000;
        User aUser = UserBuilder.aUser().withCredit(6000).build();

        aUser.withdrawCredit(negativeCredit);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryToWithdrawZeroCreditFromAccount() {
        int zeroCredit = 0;
        User aUser = UserBuilder.aUser().withCredit(6000).build();

        aUser.withdrawCredit(zeroCredit);
    }

    @Test
    public void twoUsersWithSameMailAreEquals() {
        String aMail = "firstName_lastName@domain.com";
        User aUser = UserBuilder.aUser().withMail(aMail).build();
        User anotherUser = UserBuilder.aUser().withMail(aMail).build();

        assertEquals(aUser,anotherUser);
    }

    @Test
    public void getMenusByName() {
        User aUser = UserBuilder.aUser().withName("Pocho","La Pantera").build();
        FoodService aFoodService = mock(FoodService.class);
        String aSearchString = "veggie";
        List<Menu> mockMenus = new ArrayList<>();

        mockMenus.add(mock(Menu.class));
        when(aFoodService.getMenusByName(aSearchString)).thenReturn(mockMenus);
        aUser.postFoodService(aFoodService);

        List<Menu> foundMenus = aUser.getMenusByName(aSearchString);

        assertEquals( 1, foundMenus.size() );
        verify( aFoodService, Mockito.times(1)).getMenusByName(aSearchString);
    }

    @Test
    public void getMenusByCategory() {
        User aUser = UserBuilder.aUser().withName("Pocho","La Pantera").build();
        FoodService aFoodService = mock(FoodService.class);
        List<Menu> mockMenus = new ArrayList<>();
        MenuCategory category = MenuCategory.Green;

        mockMenus.add(mock(Menu.class));
        when(aFoodService.getMenusByCategory(category)).thenReturn(mockMenus);
        aUser.postFoodService(aFoodService);

        List<Menu> foundMenus = aUser.getMenusByCategory(category);

        assertEquals( 1, foundMenus.size() );
        verify( aFoodService, Mockito.times(1)).getMenusByCategory(category);
    }

    @Test
    public void getMenusByCity() {
        User aUser = UserBuilder.aUser().withName("Pocho","La Pantera").build();
        FoodService aFoodService = mock(FoodService.class);
        Set<Menu> mockMenus = new HashSet<>();
        String city = "Berazategui";

        mockMenus.add(mock(Menu.class));
        when(aFoodService.getMenusByCity(city)).thenReturn(mockMenus);
        aUser.postFoodService(aFoodService);

        List<Menu> foundMenus = aUser.getMenusByCity(city);

        assertEquals( 1, foundMenus.size() );
        verify( aFoodService, Mockito.times(1)).getMenusByCity(city);
    }

    @Test
    public void placeClientOrder() {
        User aProvider = UserBuilder.aUser().withName("Pocho","La Pantera").build();
        User aClient = mock(User.class);
        FoodService aFoodService = mock(FoodService.class);
        when(aFoodService.getProvider()).thenReturn(aProvider);
        Menu aMenu = mock(Menu.class);
        LocalDate deliveryDate = LocalDate.now();
        DeliveryType deliveryType = DeliveryType.DeliverToAddress;
        int amount = 10;

        when(aMenu.getPrice()).thenReturn(10.0);

        aProvider.postFoodService(aFoodService);

        aProvider.placeClientOrder(aClient,aFoodService,aMenu,deliveryDate,deliveryType,amount);

        verify(aFoodService, Mockito.times(1)).placeClientOrder(aClient,aMenu,deliveryDate,deliveryType,amount);
    }

    @Test
    public void hasBannedMenus(){
        User aProvider = UserBuilder.aUser().build();
        Menu aMenu = mock(Menu.class);
        when(aMenu.banned()).thenReturn(true);
        Set<Menu> mockMenus = new HashSet<>();
        mockMenus.add(aMenu);
        FoodService aFoodService = mock(FoodService.class);
        when(aFoodService.getMenus()).thenReturn(mockMenus);

        assertFalse(aProvider.hasBannedMenus());

        aProvider.postFoodService(aFoodService);

        assertTrue(aProvider.hasBannedMenus());
    }

    @Test
    public void confirmOrders() {

        User aProvider = UserBuilder.aUser().build();
        FoodService aFoodService = mock(FoodService.class);
        FoodService otherFoodService = mock(FoodService.class);

        aProvider.postFoodService(aFoodService);
        aProvider.postFoodService(otherFoodService);

        aProvider.confirmOrders();

        verify(aFoodService, Mockito.times(1)).confirmOrders();
        verify(otherFoodService, Mockito.times(1)).confirmOrders();

    }
}