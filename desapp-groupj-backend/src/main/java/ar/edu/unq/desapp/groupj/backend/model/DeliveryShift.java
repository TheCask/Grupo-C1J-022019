package ar.edu.unq.desapp.groupj.backend.model;

import java.time.LocalTime;

public class DeliveryShift {

    private LocalTime from;
    private LocalTime to;

    public DeliveryShift(LocalTime from, LocalTime to) {

        if( from == null || to == null )
            throw new IllegalArgumentException("El turno no admite horarios de inicio/finalizacion nulos.");

        if( from.isAfter(to) )
            throw new IllegalArgumentException("El horario de inicio del turno no puede ser posterior al horario de finalizacion.");

        this.from = from;
        this.to = to;
    }

    public LocalTime getFrom() {
        return this.from;
    }

    public LocalTime getTo() {
        return this.to;
    }
}
