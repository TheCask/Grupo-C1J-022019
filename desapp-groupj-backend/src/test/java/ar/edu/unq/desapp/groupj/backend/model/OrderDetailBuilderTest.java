package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;

import java.time.LocalTime;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class OrderDetailBuilderTest {
    @Test
    public void testBuildOrder() {
        OrderDetail orderDetail = OrderDetail.Builder.anOrderDetail().
                withUser(mock(User.class)).
                withDeliveryTime(LocalTime.now()).
                withDeliveryType(DeliveryType.PickUpInStore).
                withRequestedAmount(1).
                build();

        assertEquals(1, orderDetail.getRequestedAmount());
    }
}
