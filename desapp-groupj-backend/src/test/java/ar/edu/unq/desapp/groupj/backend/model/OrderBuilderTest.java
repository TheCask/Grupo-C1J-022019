package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Test;

import java.time.LocalDate;
import static junit.framework.TestCase.assertEquals;

public class OrderBuilderTest {
    @Test
    public void testBuildOrder() {
        Order order = Order.Builder.anOrder().
                withId(1).
                withDeliveryDate(LocalDate.now()).
                withDetails(null).
                build();

        assertEquals(1, order.getId());
    }
}
