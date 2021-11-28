package org.wahlzeit.model.coordinate;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate{

    private double x;
    private double y;
    private double z;

    private double MAX_DELTA = 0.00001;

    //Parameter constructor
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    //calculates cartesian distance
    @Override
    public double getDistance(Coordinate coordinate){
        CartesianCoordinate other = coordinate.asCartesianCoordinate();
        //No nullcheck is provides, as the caller should make sure that the other object is valid
        //the calculation is split into calculating each summand...
        double x_delta_squared = Math.pow(this.getX()-other.getX(), 2);
        double y_delta_squared = Math.pow(this.getY()-other.getY(), 2);
        double z_delta_squared = Math.pow(this.getZ()-other.getZ(), 2);
        //and taking the square root of the sum
        return Math.sqrt(x_delta_squared+y_delta_squared+z_delta_squared);
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        //for better performance, it is checked if the objects are the same, first
        if (this == coordinate){
            return true;
        }
        //check if the coordinate is of the same Object type
        //this is due to my definition of equal, which requires the exact same object type
        //if one wants to compare just the values, the coordinates can always be converted to the same coordinate type beforehand
        if (!(coordinate instanceof CartesianCoordinate)){
            return false;
        }
        //only if they differ, the individual coordinates are being compared in another method
        return allCoordinatesIdentical((CartesianCoordinate) coordinate);

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
        if(obj == null || !(obj instanceof Coordinate)){
            return false;
        }
        return isEqual((Coordinate) obj);
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

    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }

    public String getIdAsString() {
        return null;
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
        double radius = getDistance(x, y, z);
        double phi = Math.atan((Math.sqrt(x*x + y*y))/z);
        double theta = resolveTheta();
        return new SphericCoordinate(phi, theta, radius);
    }

    private double resolveTheta() {
        double theta;
        if(x > 0){
            theta = Math.atan(y/x);
        }else if(x < 0){
            theta = Math.atan(y/x) + Math.PI;
        }
        else {
            theta = Math.PI /2;
        }
        return theta;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) throws ArithmeticException {
        return asSphericCoordinate().getCentralAngle(coordinate);
    }

    private double getDistance(double x, double y, double z){
        return Math.sqrt(x*x + y*y + z*z);
    }
}


