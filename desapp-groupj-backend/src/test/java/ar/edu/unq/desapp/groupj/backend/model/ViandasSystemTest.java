package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class ViandasSystemTest {

    @Test
    public void clientRegistration() {
        Client aClient = mock(Client.class);
        ViandasSystem system = ViandasSystem.getViandasSystem();

        system.registerClient(aClient);

        assertTrue(ViandasSystem.getViandasSystem().getClients().contains(aClient));
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
