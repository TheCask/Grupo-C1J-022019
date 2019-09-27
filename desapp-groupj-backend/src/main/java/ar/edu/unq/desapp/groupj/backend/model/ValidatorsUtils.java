package ar.edu.unq.desapp.groupj.backend.model;

import ar.edu.unq.desapp.groupj.backend.model.exception.MenuException;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorsUtils {

    public static void validateStringLength(String value, int minimumLength, int maximumLength, String propertyName) {
        if( !(value.length() >= minimumLength && value.length() <= maximumLength) )
            throw new IllegalArgumentException("Property '"+propertyName+"' has incorrect length.");
    }

    // use regex for validation
    public static void validateMail(String mail, String propertyName) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(mail);
        if( !matcher.find() )
            throw new IllegalArgumentException("Property '"+propertyName+"' has incorrect format.");
    }

    public static void validateIntValue(int value, int minimum, int maximum, String propertyName) {
        if( !(value >= minimum && value <= maximum) )
            throw new IllegalArgumentException("La propiedad '"+propertyName+"' tiene un valor fuera de rango.");
    }

    public static void validateDoubleValue(double value, double minimum, double maximum, String propertyName) {
        if( !(value >= minimum && value <= maximum) )
            throw new IllegalArgumentException("La propiedad '"+propertyName+"' tiene un valor fuera de rango.");
    }

    public static void validateDeliveryDate(LocalDate deliveryDate, int validDaysFromNow) {
        LocalDate now = LocalDate.now();
        Period timeToDelivery = Period.between(now,deliveryDate);
        if( timeToDelivery.isNegative() || timeToDelivery.getDays()<validDaysFromNow )
            throw new MenuException("La fecha de entrega no es valida.");
    }

    public static void validateMenuPrices(Menu aMenu) {

        if (aMenu.getPrice() <= 0 || aMenu.getMinimumAmount1Price() <= 0) {
            throw new MenuException("El precio principal y el minimo 1 son obligatorios.");
        }
        if (aMenu.getMinimumAmount1Price() >= aMenu.getPrice()) {
            throw new MenuException("No se cumple que Precio Min1 < Precio.");
        }
        if (aMenu.getMinimumAmount2Price() != 0 && aMenu.getMinimumAmount2Price() >= aMenu.getMinimumAmount1Price()) {
            throw new MenuException("No se cumple que Precio Min2 < Precio Min1.");
        }
    }

    public static void validateMenuAmounts(Menu aMenu) {
        if (aMenu.getMinimumAmount1() <= 0) {
            throw new MenuException("La Cantidad Min1 es obligatoria.");
        }
        if (aMenu.getMinimumAmount2() != 0 && aMenu.getMinimumAmount2() <= aMenu.getMinimumAmount1()) {
            throw new MenuException("No se cumple que Cantidad Min2 > Cantidad Min1.");
        }
    }
}