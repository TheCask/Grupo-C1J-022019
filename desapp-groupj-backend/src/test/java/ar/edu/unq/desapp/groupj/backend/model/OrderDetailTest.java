package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class OrderDetailTest {
    private OrderDetail orderDetail;

    @Before
    public void setUp() {
        this.orderDetail = OrderDetail.Builder.anOrderDetail().
                withUser(mock(User.class)).
                withDeliveryTime(LocalTime.now()).
                withDeliveryType(DeliveryType.DeliverToAddress).
                withRequestedAmount(1).
                build();
    }

    @Test
    public void testUserAccessors() {
        User user = mock(User.class);

        this.orderDetail.setUser(user);

        assertEquals( user, this.orderDetail.getUser() );
    }

    @Test
    public void testDeliveryTimeAccessors() {
        LocalTime now = LocalTime.now();

        this.orderDetail.setDeliveryTime(now);

        assertEquals( now, this.orderDetail.getDeliveryTime() );
    }

    @Test
    public void testDeliveryTypeAccessors() {
        DeliveryType deliveryType = DeliveryType.PickUpInStore;

        this.orderDetail.setDeliveryType(deliveryType);

        assertEquals( deliveryType, this.orderDetail.getDeliveryType() );
    }

    @Test
    public void testRequestedAmountAccessors() {
        Integer requestedAmount = 15;

        this.orderDetail.setRequestedAmount(requestedAmount);

        assertEquals( requestedAmount, this.orderDetail.getRequestedAmount() );
    }

    @Test
    public void testConfirmOrderToUser() {

        double price = 10.0;
        int requestedAmount = 10;
        Double creditToReturn = price * requestedAmount;

        User aProvider = mock(User.class);
        User aClient = mock(User.class);
        Order anOrder = mock(Order.class);
        when(anOrder.getProvider()).thenReturn(aProvider);

        OrderDetail anOrderDetail = OrderDetail.Builder.anOrderDetail().
                withOrder(anOrder).withUser(aClient).withRequestedAmount(requestedAmount).build();
        LocalDate today = LocalDate.now();

        anOrderDetail.confirmOrderToUser(today, price);

        verify(aProvider, Mockito.times(1)).withdrawCredit( creditToReturn);
        verify(aClient, Mockito.times(1)).chargeCredit( creditToReturn);
    }
}
