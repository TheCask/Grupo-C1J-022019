package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProviderTest {
    @Test
    public void withdrawCreditFromEmptyAccountNotDiscounts() {
        int credit = 5000;
        Provider aProvider = ProviderBuilder.aProvider().withCredit(0).build();

        aProvider.withdrawCredit(credit);

        assertEquals(0, aProvider.getCredit());
    }

    @Test
    public void tryToWithdrawNegativeCreditToAccountNotWithdraw() {
        int negativeCredit = -5000;
        Provider aProvider = ProviderBuilder.aProvider().withCredit(6000).build();

        aProvider.chargeCredit(negativeCredit);

        assertEquals(6000, aProvider.getCredit());
    }

}
