package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Location {
    private Coordinate coordinate;

    public Location(Coordinate coordinate) {
        if(coordinate == null){
            throw new IllegalArgumentException("Coordinate variable cannot be null");
        }
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        if(coordinate == null){
            throw new IllegalArgumentException("Coordinate variable cannot be null");
        }
        this.coordinate = coordinate;
    }

    //the writing of the coordinate values is delegated to the coordinate class
    public void writeOn(ResultSet rset) throws SQLException {
        coordinate.writeOn(rset);
    }

    //the reading of the coordinate values is delegated to the coordinate class
    public void readFrom(ResultSet rset) throws SQLException {
        coordinate.readFrom(rset);
    }

}
