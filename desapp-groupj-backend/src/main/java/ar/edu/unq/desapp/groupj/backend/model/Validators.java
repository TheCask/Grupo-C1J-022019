package ar.edu.unq.desapp.groupj.backend.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

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
}