package org.wahlzeit.model.coordinate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CartesianCoordinate implements Coordinate{

    private double x;
    private double y;
    private double z;

    double MAX_DELTA = 0.00001;

    //Parameter constructor
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //calculates cartesian distance
    public double getDistance(CartesianCoordinate other){
        //No nullcheck is provides, as the caller should make sure that the other object is valid
        //the calculation is split into calculating each summand...
        double x_delta_squared = Math.pow(this.getX()-other.getX(), 2);
        double y_delta_squared = Math.pow(this.getY()-other.getY(), 2);
        double z_delta_squared = Math.pow(this.getZ()-other.getZ(), 2);
        //and taking the square root of the sum
        return Math.sqrt(x_delta_squared+y_delta_squared+z_delta_squared);
    }

    public boolean isEqual(CartesianCoordinate other){
        //for better performance, it is checked if the objects are the same, first
        if (this == other){
            return true;
        }
        //only if they differ, the individual coordinates are being compared in another method
        return allCoordinatesIdentical(other);
    }

    //this helper method compares the individual coordinates of two coordinate instances
    //A small Delta is allowed to prevent errors due to double rounding
    private boolean allCoordinatesIdentical(CartesianCoordinate other) {
        boolean x_equal = Math.abs(this.getX() - other.getX()) < MAX_DELTA;
        boolean y_equal = Math.abs(this.getY() - other.getY()) < MAX_DELTA;
        boolean z_equal = Math.abs(this.getZ() - other.getZ()) < MAX_DELTA;
        return x_equal && y_equal && z_equal;
    }

    //the equals method is overridden as specified.
    @Override
    public boolean equals(Object obj) {
        //equals first checks for null and objects of other classes than Coordinate
        if(obj == null || !obj.getClass().equals(CartesianCoordinate.class)){
            return false;
        }
        return isEqual((CartesianCoordinate) obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y,z);
    }

    //here the actual values are written in the resultSet
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateDouble("x_coordinate", x);
        rset.updateDouble("y_coordinate", y);
        rset.updateDouble("z_coordinate", z);
    }

    //here the actual values are read from the resultSet
    public void readFrom(ResultSet rset) throws SQLException{
        x = rset.getDouble("x_coordinate");
        y = rset.getDouble("y_coordinate");
        z = rset.getDouble("z_coordinate");
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return getDistance(coordinate.asCartesianCoordinate());
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return null;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        return this.asSphericCoordinate().getCentralAngle(coordinate);
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        return this.isEqual(coordinate.asCartesianCoordinate());
    }
}


