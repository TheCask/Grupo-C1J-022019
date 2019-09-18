package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClientTest {
    @Test
    public void chargeCreditToAccount5000() {
        int credit = 5000;
        Client aClient = ClientBuilder.aClient().withCredit(0).build();

        aClient.chargeCredit(credit);

        assertEquals(credit, aClient.getCredit());
    }

    @Test
    public void tryToChargeNegativeCreditToAccountNotCharges() {
        int negativeCredit = -5000;
        Client aClient = ClientBuilder.aClient().withCredit(0).build();

        aClient.chargeCredit(negativeCredit);

        assertEquals(0, aClient.getCredit());
    }

    @Test
    public void withdrawCreditFromEmptyAccountNotDiscounts() {
        int credit = 5000;
        Client aClient = ClientBuilder.aClient().withCredit(0).build();

        aClient.withdrawCredit(credit);

        assertEquals(0, aClient.getCredit());
    }

    @Test
    public void tryToWithdrawNegativeCreditToAccountNotWithdraw() {
        int negativeCredit = -5000;
        Client aClient = ClientBuilder.aClient().withCredit(6000).build();

        aClient.chargeCredit(negativeCredit);

        assertEquals(6000, aClient.getCredit());
    }

    @Test
    public void twoClientsWithSameMailAreEquals() {
        String aMail = "firstName_lastName@domain.com";
        Client aClient = ClientBuilder.aClient().withMail(aMail).build();
        Client anotherClient = ClientBuilder.aClient().withMail(aMail).build();

        assertEquals(aClient,anotherClient);
    }
}