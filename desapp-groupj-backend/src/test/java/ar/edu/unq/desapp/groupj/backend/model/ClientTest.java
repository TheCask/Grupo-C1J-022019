package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
        import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void chargeCreditToAccount_5000() {
        int credit = 5000;
        Client aClient = ClientBuilder.aClient().withCredit(0).build();

        aClient.chargeCredit(credit);

        assertEquals(credit, aClient.getCredit());
    }
}