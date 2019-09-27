package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;
import java.time.LocalDate;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class MenuBuilderTest {
    @Test
    public void testBuildMenu() {
        Menu menu = Menu.Builder.aMenu().
                    withName("Fugazetta Rellena").
                    withDescription("Alta fugazzeta papuuu!!!!!!").
                    withCategory(MenuCategory.Pizza).
                    withDeliveryValue(10.0).
                    withAvailableFrom(LocalDate.now()).
                    withAvailableTo(LocalDate.now()).
                    withDeliveryShifts(null).
                    withAverageDeliveryTime(1).
                    withPrice(100.0).
                    withMinimumAmount1(13).
                    withMinimumAmount1Price(90).
                    withMinimumAmount2(41).
                    withMinimumAmount2Price(70).
                    withMaximumDailySales(50).
                    build();

        assertEquals( 100.0, menu.getPrice() );
    }
}
