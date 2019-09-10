package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class MenuBuilderTest {
    @Test
    public void testBuildMenu() {
        Menu menu = Menu.Builder.aMenu().
                    withService(mock(Service.class)).
                    withName("Fugazetta Rellena").
                    withDescription("Alta fugazzeta papuuu").
                    withCategory(MenuCategory.Pizza).
                    withDeliveryValue(0.0).
                    withAvailableFrom(mock(Date.class)).
                    withAvailableTo(mock(Date.class)).
                    withDeliveryShifts(null).
                    withAverageDeliveryTime(1).
                    withPrice(100.0).
                    withMinimumAmount1(3).
                    withMinimumAmount1Price(90).
                    withMinimumAmount2(10).
                    withMinimumAmount2Price(70).
                    withMaximumDailySales(50).
                    build();

        assertEquals( 100.0, menu.getPrice() );
    }
}
