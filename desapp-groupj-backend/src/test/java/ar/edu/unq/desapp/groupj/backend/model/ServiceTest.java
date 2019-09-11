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
}