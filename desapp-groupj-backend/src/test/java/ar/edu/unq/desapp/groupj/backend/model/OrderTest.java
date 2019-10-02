package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.*;

public class OrderTest {
    private Order order;

    @Before
    public void setUp() {
        this.order = Order.Builder.anOrder().
                withId(1).
                withDeliveryDate(LocalDate.now()).
                build();
    }

    @Test
    public void testIdAccessors() {
        int id = 10;

        this.order.setId(id);

        assertEquals(id, order.getId());
    }

    @Test
    public void testDeliveryDateAccessors() {
        LocalDate deliveryDate = LocalDate.now();

        this.order.setDeliveryDate(deliveryDate);

        assertEquals(deliveryDate, order.getDeliveryDate());
    }

    @Test
    public void testDetailsAccessors() {
        List<OrderDetail> details = new ArrayList<OrderDetail>();

        this.order.setDetails(details);

        assertEquals(details, order.getDetails());
    }

    @Test
    public void testAddDetail() {
        OrderDetail detail = mock(OrderDetail.class);

        this.order.addDetail(detail);

        assert( this.order.getDetails().contains(detail) );
    }

    @Test
    public void testRemoveDetail() {
        OrderDetail detail = mock(OrderDetail.class);

        this.order.addDetail(detail);

        assert( this.order.getDetails().contains(detail) );

        this.order.removeDetail(detail);

        assertFalse( this.order.getDetails().contains(detail) );
    }

    @Test
    public void testCalcRequestedAmount() {
        OrderDetail detail1 = mock(OrderDetail.class);
        OrderDetail detail2 = mock(OrderDetail.class);

        when(detail1.getRequestedAmount()).thenReturn(12);
        when(detail2.getRequestedAmount()).thenReturn(13);

        this.order.addDetail(detail1);
        this.order.addDetail(detail2);

        assertEquals( 25, this.order.getRequestedAmount() );
    }

    @Test
    public void testConfirmOrderWithMinimumPrice1() {
        double price = 50;

        int minimumAmount1ForAMenu = 10;
        double minimumPrice1ForAMenu = 35;
        double creditToReturnForMinimum1Amount = price - minimumPrice1ForAMenu;

        int minimumAmount2ForAMenu = 50;
        double minimumPrice2ForAMenu = 20;
        double creditToReturnForMinimum2Amount = price - minimumPrice2ForAMenu;

        LocalDate deliveryDate = LocalDate.now();
        Order anOrder = Order.Builder.anOrder().withDeliveryDate(deliveryDate).build();
        User aProvider = mock(User.class);

        OrderDetail anOrderDetailWithMinimum1RequestedAmount = mock(OrderDetail.class);
        when(anOrderDetailWithMinimum1RequestedAmount.getRequestedAmount()).thenReturn(minimumAmount1ForAMenu);

        OrderDetail anOrderDetailWithMinimum2RequestedAmount = mock(OrderDetail.class);
        when(anOrderDetailWithMinimum2RequestedAmount.getRequestedAmount()).thenReturn(minimumAmount2ForAMenu);

        Menu aMenu = mock(Menu.class);
        when(aMenu.getPrice()).thenReturn(price);
        when(aMenu.getMinimumAmount1()).thenReturn(minimumAmount1ForAMenu);
        when(aMenu.getMinimumAmount1Price()).thenReturn(minimumPrice1ForAMenu);
        when(aMenu.getMinimumAmount2()).thenReturn(minimumAmount2ForAMenu);
        when(aMenu.getMinimumAmount2Price()).thenReturn(minimumPrice2ForAMenu);

        anOrder.addDetail(anOrderDetailWithMinimum1RequestedAmount);
        anOrder.confirmOrder(aMenu, aProvider);
        verify(anOrderDetailWithMinimum1RequestedAmount, Mockito.times(1)).
                confirmOrderToUser(deliveryDate, creditToReturnForMinimum1Amount, aProvider);

        anOrder.addDetail(anOrderDetailWithMinimum2RequestedAmount);
        anOrder.confirmOrder(aMenu, aProvider);
        verify(anOrderDetailWithMinimum2RequestedAmount, Mockito.times(1)).
                confirmOrderToUser(deliveryDate, creditToReturnForMinimum2Amount, aProvider);
    }
}
