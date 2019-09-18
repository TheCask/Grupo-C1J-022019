package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import org.mockito.Mockito;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ViandasSystemTest {

    @Test
    public void clientRegistration() {
        Client aClient = mock(Client.class);
        ViandasSystem system = ViandasSystem.getViandasSystem();

        system.registerClient(aClient);

        assertTrue(system.getClients().contains(aClient));
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryToRegisterAClientAlreadyRegisteredWithSameMailFails() {
        String aMail = "firstName_lastName@domain.com";
        Client aClient = ClientBuilder.aClient().withMail(aMail).build();
        Client anotherClient = ClientBuilder.aClient().withMail(aMail).build();

        ViandasSystem system = ViandasSystem.getViandasSystem();
        system.registerClient(aClient);

        system.registerClient(anotherClient);
    }

    @Test
    public void chargeCreditToClient() {
        int credit = 555;
        Client aClient = mock(Client.class);
        when(aClient.chargeCredit(anyInt())).thenReturn(anyInt());
        ViandasSystem system = ViandasSystem.getViandasSystem();

        system.chargeCreditToClient(credit, aClient);

        verify(aClient, Mockito.times(1)).chargeCredit(anyInt());
    }

    @Test
    public void withdrawCreditFromClient() {
        int creditToWithdraw = 555;
        int newCredit = 125;
        Client aClient = mock(Client.class);
        when(aClient.withdrawCredit(anyInt())).thenReturn(newCredit);
        ViandasSystem system = ViandasSystem.getViandasSystem();

        int expectedCredit = system.withdrawCreditFromClient(creditToWithdraw, aClient);

        verify(aClient, Mockito.times(1)).withdrawCredit(creditToWithdraw);
        assertEquals(newCredit, expectedCredit);
    }

    @Test
    public void uploadService() {
        Service aService = mock(Service.class);
        Client aClient = mock(Client.class);
        ViandasSystem system = ViandasSystem.getViandasSystem();

        system.clientPostService(aClient, aService);

        verify(aClient, Mockito.times(1)).postService(aService);
    }

    @Test
    public void addMenuToService() {
        Service aService = mock(Service.class);
        Menu aMenu = mock(Menu.class);
        ViandasSystem system = ViandasSystem.getViandasSystem();

        system.addMenuToService(aMenu, aService);

        verify(aService, Mockito.times(1)).addMenu(aMenu);
    }
 }