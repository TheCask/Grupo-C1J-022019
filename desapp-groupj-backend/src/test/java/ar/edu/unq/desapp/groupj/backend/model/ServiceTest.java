package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
}