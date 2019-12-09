package ar.edu.unq.desapp.groupj.backend.model;

import ar.edu.unq.desapp.groupj.backend.model.exception.MenuException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class MenuTest {

    private Menu menu;

    @Before
    public void setUp() {
        this.menu = Menu.Builder.aMenu().
                withName("Fugazetta Rellena").
                withDescription("Alta fugazzeta papuuu").
                withCategory(MenuCategory.Pizza).
                withDeliveryValue(20.0).
                withAvailableFrom(LocalDate.now()).
                withAvailableTo(LocalDate.now()).
                withDeliveryShifts(null).
                withAverageDeliveryTime(1.0).
                withPrice(100.0).
                withMinimumAmount1(13).
                withMinimumAmount1Price(78.0).
                withMinimumAmount2(50).
                withMinimumAmount2Price(63.0).
                withMaximumDailySales(50).
                build();
    }

    @Test
    public void testNameAccessors() {
        String name = "Fugazetta Rellena";

        this.menu.setName(name);

        assertEquals(name, this.menu.getName());
    }

    @Test
    public void testDescriptionAccessors() {
        String description = "Alta Redonda Ameo!!!!!";

        this.menu.setDescription(description);

        assertEquals(description, this.menu.getDescription());
    }

    @Test
    public void testCategoryAccessors() {
        MenuCategory category = MenuCategory.Pizza;

        this.menu.setCategory(category);

        assertEquals(category, this.menu.getCategory());
    }

    @Test
    public void testDeliveryValueAccessors() {
        double deliveryValue = 26.7;

        this.menu.setDeliveryValue(deliveryValue);

        assertEquals(deliveryValue, this.menu.getDeliveryValue());
    }

    @Test
    public void testAvailableFromAccessors() {
        LocalDate from = LocalDate.now();

        this.menu.setAvailableFrom(from);

        assertEquals(from, this.menu.getAvailableFrom());
    }

    @Test
    public void testAvailableToAccessors() {
        LocalDate to = LocalDate.now();

        this.menu.setAvailableTo(to);

        assertEquals(to, this.menu.getAvailableTo());
    }

    @Test
    public void testDeliveryShiftsAccessors() {
        HashSet<DeliveryShift> deliveryShifts = new HashSet<>();

        this.menu.setDeliveryShifts(deliveryShifts);

        assertEquals(deliveryShifts, this.menu.getDeliveryShifts());
    }

    @Test
    public void testPriceAccessors() {
        double price = 76.9;

        this.menu.setPrice(price);

        assertEquals(price, this.menu.getPrice());
    }

    @Test
    public void testAverageDeliveryTimeAccessors() {
        double averageDeliveryTime = 0.6;

        this.menu.setAverageDeliveryTime(averageDeliveryTime);

        assertEquals(averageDeliveryTime, this.menu.getAverageDeliveryTime());
    }

    @Test
    public void testMinimumAmount1Accessors() {
        Integer amount = 13;

        this.menu.setMinimumAmount1(amount);

        assertEquals(amount, this.menu.getMinimumAmount1());
    }

    @Test
    public void testMinimumAmount1PriceAccessors() {
        double price = 76.9;

        this.menu.setMinimumAmount1Price(price);

        assertEquals(price, this.menu.getMinimumAmount1Price());
    }

    @Test
    public void testMinimumAmount2Accessors() {
        Integer amount = 43;

        this.menu.setMinimumAmount2(amount);

        assertEquals(amount, this.menu.getMinimumAmount2());
    }

    @Test
    public void testMinimumAmount2PriceAccessors() {
        Double price = 76.9;

        this.menu.setMinimumAmount2Price(price);

        assertEquals(price, this.menu.getMinimumAmount2Price());
    }

    @Test
    public void testMaximumDailySalesAccessors() {
        Integer sales = 10;

        this.menu.setMaximumDailySales(sales);

        assertEquals(sales, this.menu.getMaximumDailySales());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNameInvalidLengthRaisesException() {
        this.menu.setName("FYI");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDescriptionInvalidLengthRaisesException() {
        this.menu.setName("FYI");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidDeliveryValueRaisesException() {
        this.menu.setDeliveryValue(0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidMinimumAmount1RaisesException() {
        this.menu.setMinimumAmount1(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidMinimumAmount1PriceRaisesException() {
        this.menu.setMinimumAmount1Price(2000.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidMinimumAmount2RaisesException() {
        this.menu.setMinimumAmount2(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidMinimumAmount2PriceRaisesException() {
        this.menu.setMinimumAmount2Price(2000.0);
    }

    @Test
    public void testNoRates() {
        HashSet<Rate> rates = new HashSet<>();

        this.menu.setRates(rates);

        assertEquals((Integer)0, this.menu.getRateCount());
    }

    @Test
    public void testAddRates() {
        HashSet<Rate> rates = new HashSet<>();

        this.menu.setRates(rates);
        this.menu.addRate(new Rate(this.menu, mock(User.class), 2));
        this.menu.addRate(new Rate(this.menu, mock(User.class), 2));
        this.menu.addRate(new Rate(this.menu, mock(User.class), 2));

        assertEquals((Integer)3, this.menu.getRateCount());
        assertEquals((Integer)2, this.menu.getAverageRate());
    }

    @Test
    public void placeClientNonExistingOrder() {
        LocalDate deliveryDate = LocalDate.now().plusDays(5);
        LocalTime deliveryTime = LocalTime.now();
        int amount = 10;

        Order anOrder = placeClientOrder(deliveryDate,deliveryTime,amount);

        assertEquals(1, this.menu.getOrders().size() );
        assertTrue(this.menu.getOrders().contains(anOrder));
        assertEquals(amount, anOrder.getRequestedAmount());
    }

    @Test
    public void placeClientExistingOrder() {
        LocalDate deliveryDate = LocalDate.now().plusDays(5);
        LocalTime deliveryTime = LocalTime.now();
        int amount = 10;

        Order anOrder = placeClientOrder(deliveryDate,deliveryTime,amount);
        placeClientOrder(deliveryDate,deliveryTime,amount);

        assertEquals(1, this.menu.getOrders().size() );
        assertTrue(this.menu.getOrders().contains(anOrder));
        assertEquals(amount*2, anOrder.getRequestedAmount());
    }

    private Order placeClientOrder(LocalDate deliveryDate, LocalTime deliveryTime, int amount) {
        User aClient = mock(User.class);
        DeliveryType deliveryType = DeliveryType.DeliverToAddress;
        return this.menu.placeClientOrder(aClient,deliveryDate,deliveryTime,deliveryType,amount);
    }

    @Test (expected = MenuException.class)
    public void placeClientOrderWithPastDeliveryDate() {
        LocalDate deliveryDate = LocalDate.now().minusDays(3);
        LocalTime deliveryTime = LocalTime.now();
        int amount = 10;

        placeClientOrder(deliveryDate,deliveryTime,amount);
    }

    @Test (expected = MenuException.class)
    public void placeClientOrderWithDeliveryDateLessThan48hs() {
        LocalDate deliveryDate = LocalDate.now().plusDays(1);
        LocalTime deliveryTime = LocalTime.now();
        int amount = 10;

        placeClientOrder(deliveryDate,deliveryTime,amount);
    }

    @Test
    public void confirmOrdersForDeliveryToday() {

        int menuAmount = 4;
        LocalDate today = LocalDate.now();

        Order orderToDeliverToday = mock(Order.class);
        when(orderToDeliverToday.getDeliveryDate()).thenReturn(today);

        menu.addOrder(orderToDeliverToday);

        menu.confirmOrders();

        verify(orderToDeliverToday, Mockito.times(1)).confirmOrder();
    }

    @Test
    public void notConfirmOrdersForDeliveryYesterdayOrTomorrow() {

        int menuAmount = 4;
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate yesterday = LocalDate.now().minusDays(1);

        Order orderToDeliverTomorrow = mock(Order.class);
        when(orderToDeliverTomorrow.getDeliveryDate()).thenReturn(tomorrow);
        Order orderToDeliverYesterday = mock(Order.class);
        when(orderToDeliverYesterday.getDeliveryDate()).thenReturn(yesterday);

        menu.addOrder(orderToDeliverTomorrow);
        menu.addOrder(orderToDeliverYesterday);

        menu.confirmOrders();

        verify(orderToDeliverTomorrow, Mockito.times(0)).confirmOrder();
        verify(orderToDeliverYesterday, Mockito.times(0)).confirmOrder();
    }


}
