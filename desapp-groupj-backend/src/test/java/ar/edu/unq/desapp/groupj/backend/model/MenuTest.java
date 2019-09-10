package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class MenuTest {

    private Menu menu;

    @Before
    public void setUp() {
        this.menu = Menu.Builder.aMenu().
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
    }

    @Test
    public void testCreateMenu() {
        Menu m = new Menu( mock(Service.class),
                "Fugazetta Rellena",
                "Alta fugazzeta papuuu",
                MenuCategory.Pizza,
                0.0,
                mock(Date.class),
                mock(Date.class),
                null,
                1,
                100.0,
                3,
                90,
                10,
                70,
                50);

        assertEquals( 100.0, m.getPrice() );
    }

    @Test
    public void testServiceAccesors() {
        Service service = mock(Service.class);

        this.menu.setService(service);

        assertEquals( service, this.menu.getService() );
    }

    @Test
    public void testNameAccessors() {
        String name = "Fugazetta Rellena";

        this.menu.setName(name);

        assertEquals( name, this.menu.getName() );
    }

    @Test
    public void testDescriptionAccessors() {
        String description = "Alta Redonda Ameo!";

        this.menu.setDescription(description);

        assertEquals( description, this.menu.getDescription() );
    }

    @Test
    public void testCategoryAccessors() {
        MenuCategory category = MenuCategory.Pizza;

        this.menu.setCategory(category);

        assertEquals( category, this.menu.getCategory() );
    }

    @Test
    public void testDeliveryValueAccessors() {
        double deliveryValue = 6.7;

        this.menu.setDeliveryValue(deliveryValue);

        assertEquals( deliveryValue, this.menu.getDeliveryValue() );
    }

    @Test
    public void testAvailableFromAccessors() {
        Date from = mock(Date.class);

        this.menu.setAvailableFrom(from);

        assertEquals( from, this.menu.getAvailableFrom() );
    }

    @Test
    public void testAvailableToAccessors() {
        Date to = mock(Date.class);

        this.menu.setAvailableTo(to);

        assertEquals( to, this.menu.getAvailableTo() );
    }

    @Test
    public void testDeliveryShiftsAccessors() {
        List<DeliveryShift> deliveryShifts = new ArrayList<DeliveryShift>();

        this.menu.setDeliveryShifts(deliveryShifts);

        assertEquals( deliveryShifts, this.menu.getDeliveryShifts() );
    }

    @Test
    public void testPriceAccessors() {
        double price = 76.9;

        this.menu.setPrice(price);

        assertEquals( price, this.menu.getPrice() );
    }

    @Test
    public void testAverageDeliveryTimeAccessors() {
        double averageDeliveryTime = 0.6;

        this.menu.setAverageDeliveryTime(averageDeliveryTime);

        assertEquals( averageDeliveryTime, this.menu.getAverageDeliveryTime() );
    }

    @Test
    public void testMinimumAmount1Accessors() {
        int amount = 3;

        this.menu.setMinimumAmount1(amount);

        assertEquals( amount, this.menu.getMinimumAmount1() );
    }

    @Test
    public void testMinimumAmount1PriceAccessors() {
        double price = 76.9;

        this.menu.setMinimumAmount1Price(price);

        assertEquals( price, this.menu.getMinimumAmount1Price() );
    }

    @Test
    public void testMinimumAmount2Accessors() {
        int amount = 3;

        this.menu.setMinimumAmount2(amount);

        assertEquals( amount, this.menu.getMinimumAmount2() );
    }

    @Test
    public void testMinimumAmount2PriceAccessors() {
        double price = 76.9;

        this.menu.setMinimumAmount2Price(price);

        assertEquals( price, this.menu.getMinimumAmount2Price() );
    }

    @Test
    public void testMaximumDailySalesAccessors() {
        int sales = 10;

        this.menu.setMaximumDailySales(sales);

        assertEquals( sales, this.menu.getMaximumDailySales() );
    }

}
