package ar.edu.unq.desapp.groupj.backend.model;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
}
