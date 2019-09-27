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
        int requestedAmount = 15;

        this.orderDetail.setRequestedAmount(requestedAmount);

        assertEquals( requestedAmount, this.orderDetail.getRequestedAmount() );
    }
}
