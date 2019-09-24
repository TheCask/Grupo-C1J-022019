package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
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
    public void findMenuByName() {
        User aUser = UserBuilder.aUser().withName("Pocho","La Pantera").build();
        Service aService = mock(Service.class);
        String aSearchString = "veggie";
        List<Menu> mockMenus = new ArrayList<Menu>();

        mockMenus.add(mock(Menu.class));
        when(aService.findMenuByName(aSearchString)).thenReturn(mockMenus);
        aUser.postService(aService);

        List<Menu> foundMenus = aUser.findMenuByName(aSearchString);

        assertEquals( 1, foundMenus.size() );
        verify( aService, Mockito.times(1)).findMenuByName(aSearchString);
    }
}