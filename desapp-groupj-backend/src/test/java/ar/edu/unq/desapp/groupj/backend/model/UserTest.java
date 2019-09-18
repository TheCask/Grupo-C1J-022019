package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void chargeCreditToAccount5000() {
        int credit = 5000;
        User aUser = UserBuilder.aUser().withCredit(0).build();

        aUser.chargeCredit(credit);

        assertEquals(credit, aUser.getCredit());
    }

    @Test
    public void tryToChargeNegativeCreditToAccountNotCharges() {
        int negativeCredit = -5000;
        User aUser = UserBuilder.aUser().withCredit(0).build();

        aUser.chargeCredit(negativeCredit);

        assertEquals(0, aUser.getCredit());
    }

    @Test
    public void withdrawCreditFromEmptyAccountNotDiscounts() {
        int credit = 5000;
        User aUser = UserBuilder.aUser().withCredit(0).build();

        aUser.withdrawCredit(credit);

        assertEquals(0, aUser.getCredit());
    }

    @Test
    public void tryToWithdrawNegativeCreditToAccountNotWithdraw() {
        int negativeCredit = -5000;
        User aUser = UserBuilder.aUser().withCredit(6000).build();

        aUser.chargeCredit(negativeCredit);

        assertEquals(6000, aUser.getCredit());
    }

    @Test
    public void twoUsersWithSameMailAreEquals() {
        String aMail = "firstName_lastName@domain.com";
        User aUser = UserBuilder.aUser().withMail(aMail).build();
        User anotherUser = UserBuilder.aUser().withMail(aMail).build();

        assertEquals(aUser,anotherUser);
    }
}