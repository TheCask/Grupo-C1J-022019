package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class OrderBuilderTest {
    @Test
    public void testBuildOrder() {
        Order order = Order.Builder.anOrder().
                withId(1).
                withMenu(mock(Menu.class)).
                withDeliveryDate(mock(Date.class)).
                withDetails(null).
                build();

        assertEquals(1, order.getId());
    }
}
