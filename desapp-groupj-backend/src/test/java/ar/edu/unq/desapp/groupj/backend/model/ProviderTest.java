package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.mock;

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

    @Test
    public void addMenuToProvider() {
        Menu menuMock = mock(Menu.class);

        Provider aProvider = ProviderBuilder.aProvider().build();
        assertEquals(0, aProvider.getMenus().size());

        aProvider.addMenu(menuMock);
        assertEquals(1, aProvider.getMenus().size());
        assertTrue(aProvider.getMenus().contains(menuMock));
    }

    @Test
    public void removeMenuFromProvider() {
        Menu menuMock = mock(Menu.class);

        Provider aProvider = ProviderBuilder.aProvider().withMenu(menuMock).build();
        assertEquals(1, aProvider.getMenus().size());

        aProvider.deleteMenu(menuMock);
        assertFalse(aProvider.getMenus().contains(menuMock));
        assertEquals(0, aProvider.getMenus().size());
    }

    @Test
    public void updateMenuFromProvider() {
        Menu menuMock1 = mock(Menu.class);
        Menu menuMock2 = mock(Menu.class);

        Provider aProvider = ProviderBuilder.aProvider().withMenu(menuMock1).build();
        assertEquals(1, aProvider.getMenus().size());
        assertTrue(aProvider.getMenus().contains(menuMock1));

        aProvider.updateMenu(menuMock1, menuMock2);
        assertFalse(aProvider.getMenus().contains(menuMock1));
        assertTrue(aProvider.getMenus().contains(menuMock2));
        assertEquals(1, aProvider.getMenus().size());
    }
}
