package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import org.mockito.Mockito;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ViandasSystemTest {

    @Test
    public void UserRegistration() {
        User aUser = mock(User.class);
        ViandasSystem system = ViandasSystem.getViandasSystem();

        system.registerUser(aUser);

        assertTrue(system.getUsers().contains(aUser));
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryToRegisterAUserAlreadyRegisteredWithSameMailFails() {
        String aMail = "firstName_lastName@domain.com";
        User aUser = UserBuilder.aUser().withMail(aMail).build();
        User anotherUser = UserBuilder.aUser().withMail(aMail).build();

        ViandasSystem system = ViandasSystem.getViandasSystem();
        system.registerUser(aUser);

        system.registerUser(anotherUser);
    }

    @Test
    public void chargeCreditToUser() {
        int credit = 555;
        User aUser = mock(User.class);
        when(aUser.chargeCredit(anyInt())).thenReturn(anyInt());
        ViandasSystem system = ViandasSystem.getViandasSystem();

        system.chargeCreditToUser(credit, aUser);

        verify(aUser, Mockito.times(1)).chargeCredit(anyInt());
    }

    @Test
    public void withdrawCreditFromUser() {
        int creditToWithdraw = 555;
        int newCredit = 125;
        User aUser = mock(User.class);
        when(aUser.withdrawCredit(anyInt())).thenReturn(newCredit);
        ViandasSystem system = ViandasSystem.getViandasSystem();

        int expectedCredit = system.withdrawCreditFromUser(creditToWithdraw, aUser);

        verify(aUser, Mockito.times(1)).withdrawCredit(creditToWithdraw);
        assertEquals(newCredit, expectedCredit);
    }

    @Test
    public void uploadService() {
        Service aService = mock(Service.class);
        User aUser = mock(User.class);
        ViandasSystem system = ViandasSystem.getViandasSystem();

        system.userPostService(aUser, aService);

        verify(aUser, Mockito.times(1)).postService(aService);
    }

    @Test
    public void addMenuToService() {
        Service aService = mock(Service.class);
        Menu aMenu = mock(Menu.class);
        ViandasSystem system = ViandasSystem.getViandasSystem();

        system.addMenuToService(aMenu, aService);

        verify(aService, Mockito.times(1)).addMenu(aMenu);
    }

    @Test
    public void findMenuByName() {
        ViandasSystem system = ViandasSystem.getViandasSystem();
        User aUser = UserBuilder.aUser().withName("Pocho","La Pantera").build();
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

        List<Menu> foundMenus = system.findMenuByName("veggie");

        assertEquals( 2, foundMenus.size() );
        assertTrue( foundMenus.contains(aMenuVeggie1) );
        assertTrue( foundMenus.contains(aMenuVeggie2) );
    }
 }