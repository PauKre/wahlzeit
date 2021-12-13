package org.wahlzeit.model;

import org.wahlzeit.model.coordinate.Coordinate;
import org.wahlzeit.model.coordinate.CoordinateException;
import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Location extends DataObject {
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

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }

    @Override
    public String getIdAsString() {
        return null;
    }

    //the reading of the coordinate values is delegated to the coordinate class
    public void readFrom(ResultSet rset) throws SQLException {
        try {
            coordinate.readFrom(rset);
        } catch (CoordinateException coordinateException) {
            coordinateException.printStackTrace();
        }
    }

}
