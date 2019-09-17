package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class OrderDetailTest {
    private OrderDetail orderDetail;

    @Before
    public void setUp() {
        this.orderDetail = OrderDetail.Builder.anOrderDetail().
                withOrder(mock(Order.class)).
                withClient(mock(Client.class)).
                withDeliveryTime(LocalTime.now()).
                withDeliveryType(DeliveryType.DeliverToClientAddress).
                withRequestedAmount(1).
                build();
    }

    @Test
    public void testOrderAccessors() {
        Order order = mock(Order.class);

        this.orderDetail.setOrder(order);

        assertEquals( order, this.orderDetail.getOrder() );
    }

    @Test
    public void testClientAccessors() {
        Client client = mock(Client.class);

        this.orderDetail.setClient(client);

        assertEquals( client, this.orderDetail.getClient() );
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
        int requestedAmount = 15;

        this.orderDetail.setRequestedAmount(requestedAmount);

        assertEquals( requestedAmount, this.orderDetail.getRequestedAmount() );
    }
}
