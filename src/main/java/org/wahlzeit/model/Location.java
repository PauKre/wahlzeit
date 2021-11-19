package org.wahlzeit.model;

import org.wahlzeit.model.coordinate.CartesianCoordinate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Location {
    private CartesianCoordinate cartesianCoordinate;

    public Location(CartesianCoordinate cartesianCoordinate) {
        if(cartesianCoordinate == null){
            throw new IllegalArgumentException("Coordinate variable cannot be null");
        }
        this.cartesianCoordinate = cartesianCoordinate;
    }

    public CartesianCoordinate getCoordinate() {
        return cartesianCoordinate;
    }

    public void setCoordinate(CartesianCoordinate cartesianCoordinate) {
        if(cartesianCoordinate == null){
            throw new IllegalArgumentException("Coordinate variable cannot be null");
        }
        this.cartesianCoordinate = cartesianCoordinate;
    }

    //the writing of the coordinate values is delegated to the coordinate class
    public void writeOn(ResultSet rset) throws SQLException {
        cartesianCoordinate.writeOn(rset);
    }

    //the reading of the coordinate values is delegated to the coordinate class
    public void readFrom(ResultSet rset) throws SQLException {
        cartesianCoordinate.readFrom(rset);
    }

}
