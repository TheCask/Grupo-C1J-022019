package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import org.mockito.Mockito;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
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
        Service aService = mock(Service.class);
        String aSearchString = "veggie";
        List<Menu> mockMenus = new ArrayList<Menu>();

        mockMenus.add(mock(Menu.class));
        when(aService.getMenusByName(aSearchString)).thenReturn(mockMenus);
        aUser.postService(aService);

        List<Menu> foundMenus = aUser.getMenusByName(aSearchString);

        assertEquals( 1, foundMenus.size() );
        verify( aService, Mockito.times(1)).getMenusByName(aSearchString);
    }

    @Test
    public void getMenusByCategory() {
        User aUser = UserBuilder.aUser().withName("Pocho","La Pantera").build();
        Service aService = mock(Service.class);
        List<Menu> mockMenus = new ArrayList<Menu>();
        MenuCategory category = MenuCategory.Green;

        mockMenus.add(mock(Menu.class));
        when(aService.getMenusByCategory(category)).thenReturn(mockMenus);
        aUser.postService(aService);

        List<Menu> foundMenus = aUser.getMenusByCategory(category);

        assertEquals( 1, foundMenus.size() );
        verify( aService, Mockito.times(1)).getMenusByCategory(category);
    }

    @Test
    public void getMenusByCity() {
        User aUser = UserBuilder.aUser().withName("Pocho","La Pantera").build();
        Service aService = mock(Service.class);
        List<Menu> mockMenus = new ArrayList<Menu>();
        String city = "Berazategui";

        mockMenus.add(mock(Menu.class));
        when(aService.getMenusByCity(city)).thenReturn(mockMenus);
        aUser.postService(aService);

        List<Menu> foundMenus = aUser.getMenusByCity(city);

        assertEquals( 1, foundMenus.size() );
        verify( aService, Mockito.times(1)).getMenusByCity(city);
    }

    @Test
    public void placeClientOrder() {
        User aProvider = UserBuilder.aUser().withName("Pocho","La Pantera").build();
        User aClient = mock(User.class);
        Service aService = mock(Service.class);
        Menu aMenu = mock(Menu.class);
        Date deliveryDate = mock(Date.class);
        DeliveryType deliveryType = DeliveryType.DeliverToAddress;
        int amount = 10;

        aProvider.postService(aService);

        aProvider.placeClientOrder(aClient,aService,aMenu,deliveryDate,deliveryType,amount);

        verify(aService, Mockito.times(1)).placeClientOrder(aClient,aMenu,deliveryDate,deliveryType,amount);
    }
}