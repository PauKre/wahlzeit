package org.wahlzeit.model.coordinate;

public class CoordinateException extends Exception{

    public CoordinateException(String message){
        super(message);
    }

    public CoordinateException(ArithmeticException exception){
        super(exception);
    }
}
