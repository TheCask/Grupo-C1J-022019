package ar.edu.unq.desapp.groupj.backend.model;

import java.time.LocalTime;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class DeliveryShiftTest {

    @Test
    public void testCreateValidDeliveryShift() {
        LocalTime from = LocalTime.MIN;
        LocalTime to = from.plusHours(3);

        DeliveryShift shift = new DeliveryShift(from,to);

        assertEquals( from, shift.getFrom() );
        assertEquals( to, shift.getTo() );
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullFromToRaisesException() {
        DeliveryShift shift = new DeliveryShift(null,null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFromAfterToRaisesException() {
        LocalTime from = LocalTime.MIN;
        DeliveryShift shift = new DeliveryShift(from.plusHours(3),from);
    }
}
