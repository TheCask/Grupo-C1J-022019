package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceTest {

    @Test
    public void addSiteToService() {
        String site = "vegan_meals_now.com";
        Service aService = ServiceBuilder.aService().build();

        aService.addSite(site);

        assertEquals(site, aService.getSite());
    }

    @Test
    public void setValidMail() {
        String validMail = "myName@myDomain.com";
        Service aService = ServiceBuilder.aService().build();

        aService.setMail(validMail);

        assertEquals(validMail, aService.getMail());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidShortLengthDescription() {
        String shortDescription = "short description";
        Service aService = ServiceBuilder.aService().build();

        aService.setDescription(shortDescription);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidLongLengthDescription() {
        String longDescription = "A                                                                              " +
                "                                                                                                " +
                "                                                                                                " +
                "                                                                                long description";
        Service aService = ServiceBuilder.aService().build();

        aService.setDescription(longDescription);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidFormatMailWithoutAt() {
        String mailWithoutAt = "mailNameDomain.com";
        Service aService = ServiceBuilder.aService().build();

        aService.setMail(mailWithoutAt);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidFormatMailWithoutDomainDot() {
        String mailWithoutDot = "mail.Name@Domaincom";
        Service aService = ServiceBuilder.aService().build();

        aService.setMail(mailWithoutDot);
    }

    @Test
    public void addMenu() {
        Menu menuMock = mock(Menu.class);

        Service aService = ServiceBuilder.aService().build();
        assertEquals(0, aService.getMenus().size());

        aService.addMenu(menuMock);
        assertEquals(1, aService.getMenus().size());
        assertTrue(aService.getMenus().contains(menuMock));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addMenuToServiceWithMaximumActiveMenus() {
        Menu menuMock = mock(Menu.class);
        when(menuMock.active()).thenReturn(true);
        Service aService = ServiceBuilder.aService().build();
        for (int i = 1; i <= 20; i++) { aService.addMenu(menuMock); }
        assertEquals(20, aService.getMenus().size());

        aService.addMenu(menuMock);
    }

    @Test
    public void removeMenuFromService() {
        Menu menuMock = mock(Menu.class);

        Service aService = ServiceBuilder.aService().build();
        aService.addMenu(menuMock);
        assertEquals(1, aService.getMenus().size());

        aService.deleteMenu(menuMock);
        assertFalse(aService.getMenus().contains(menuMock));
        assertEquals(0, aService.getMenus().size());
    }

    @Test
    public void updateMenuFromClient() {
        Menu menuMock1 = mock(Menu.class);
        Menu menuMock2 = mock(Menu.class);

        Service aService = ServiceBuilder.aService().build();
        aService.addMenu(menuMock1);
        assertEquals(1, aService.getMenus().size());
        assertTrue(aService.getMenus().contains(menuMock1));

        aService.updateMenu(menuMock1, menuMock2);
        assertFalse(aService.getMenus().contains(menuMock1));
        assertTrue(aService.getMenus().contains(menuMock2));
        assertEquals(1, aService.getMenus().size());
    }
}