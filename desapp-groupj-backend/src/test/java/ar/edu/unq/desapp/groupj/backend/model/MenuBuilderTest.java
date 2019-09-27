package ar.edu.unq.desapp.groupj.backend.model;

import ar.edu.unq.desapp.groupj.backend.model.exception.MenuException;
import org.junit.Test;
import java.time.LocalDate;
import static junit.framework.TestCase.assertEquals;

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
                    withMinimumAmount1Price(78.0).
                    withMinimumAmount2(41).
                    withMinimumAmount2Price(63.0).
                    withMaximumDailySales(50).
                    build();

        assertEquals( 100.0, menu.getPrice() );
    }

    @Test (expected = MenuException.class)
    public void createAnInvalidZeroPriceMenu() {
        int invalidZeroPrice = 0;

        Menu.Builder.aMenu().
                withName("Fugazetta Rellena").
                withDescription("Alta fugazzeta papuuu!!!!!!").
                withCategory(MenuCategory.Pizza).
                withAvailableFrom(LocalDate.now()).
                withAvailableTo(LocalDate.now()).
                withDeliveryShifts(null).
                withAverageDeliveryTime(1).
                withPrice(invalidZeroPrice).
                withMinimumAmount1(13).
                withMinimumAmount1Price(78.0).
                withMaximumDailySales(50).
                build();
    }

    @Test (expected = MenuException.class)
    public void createAnInvalidMin1PriceGreaterThanPriceMenu() {
        double validPrice = 100.0;
        double invalidMin1PriceGreaterThanPrice = 110.0;

        Menu.Builder.aMenu().
                withName("Fugazetta Rellena").
                withDescription("Alta fugazzeta papuuu!!!!!!").
                withCategory(MenuCategory.Pizza).
                withAvailableFrom(LocalDate.now()).
                withAvailableTo(LocalDate.now()).
                withDeliveryShifts(null).
                withAverageDeliveryTime(1).
                withPrice(validPrice).
                withMinimumAmount1(13).
                withMinimumAmount1Price(invalidMin1PriceGreaterThanPrice).
                withMaximumDailySales(50).
                build();
    }

    @Test (expected = MenuException.class)
    public void createAnInvalidMin2PriceGreaterThanMin1PriceMenu() {
        double invalidMin2PriceGreaterThanMin1Price = 78.9;
        double validMin1Price = 56.0;
        double validPrice = 100.0;

        Menu.Builder.aMenu().
                withName("Fugazetta Rellena").
                withDescription("Alta fugazzeta papuuu!!!!!!").
                withCategory(MenuCategory.Pizza).
                withAvailableFrom(LocalDate.now()).
                withAvailableTo(LocalDate.now()).
                withDeliveryShifts(null).
                withAverageDeliveryTime(1).
                withPrice(validPrice).
                withMinimumAmount1(13).
                withMinimumAmount1Price(validMin1Price).
                withMinimumAmount2(41).
                withMinimumAmount2Price(invalidMin2PriceGreaterThanMin1Price).
                withMaximumDailySales(50).
                build();
    }

    @Test (expected = MenuException.class)
    public void createAnInvalidMin1AmountGreaterThanMin2AmountMenu() {
        int invalidMin1AmountGreaterThanMin2Amount = 69;
        int validMin2Amount = 40;

        Menu.Builder.aMenu().
                withName("Fugazetta Rellena").
                withDescription("Alta fugazzeta papuuu!!!!!!").
                withCategory(MenuCategory.Pizza).
                withAvailableFrom(LocalDate.now()).
                withAvailableTo(LocalDate.now()).
                withDeliveryShifts(null).
                withAverageDeliveryTime(1).
                withPrice(100.0).
                withMinimumAmount1(invalidMin1AmountGreaterThanMin2Amount).
                withMinimumAmount1Price(78.0).
                withMinimumAmount2(validMin2Amount).
                withMinimumAmount2Price(63.0).
                withMaximumDailySales(50).
                build();
    }
}
