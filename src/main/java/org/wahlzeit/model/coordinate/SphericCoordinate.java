package org.wahlzeit.model.coordinate;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate{

    private double phi;

    private double theta;

    private double radius;

    double MAX_DELTA = 0.00001;

    public SphericCoordinate(double phi, double theta, double radius) {
        this. phi = cleanAngle(phi);
        this.theta = cleanAngle(theta);
        this.radius = radius;
    }

    //converts angles to values between 0 and 2 Pi
    private double cleanAngle(double angle) {
        return angle % (2 * Math.PI);
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = cleanAngle(phi);
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = cleanAngle(theta);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius * Math.sin(phi) * Math.cos(theta);
        double y = radius * Math.sin(phi) * Math.sin(theta);
        double z = radius * Math.cos(phi);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return asCartesianCoordinate().getCartesianDistance(coordinate);
    }


    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) throws ArithmeticException {
        SphericCoordinate other = coordinate.asSphericCoordinate();
        try {
            return(Math.acos(Math.sin(theta)*Math.sin(other.getTheta()) + Math.cos(theta)*Math.cos(other.getTheta())*Math.cos(phi-other.getPhi())));
        }catch (Exception e){
            throw new ArithmeticException("Error in Calculating the Central Angle!");
        }
    }

    public boolean isEqual(SphericCoordinate other) {
        if(this == other){
            return true;
        }
        return allValuesIdetical(other);

    }

    private boolean allValuesIdetical(SphericCoordinate other) {
        boolean phi_equal = Math.abs(this.getPhi() - other.getPhi()) < MAX_DELTA;
        boolean theta_equal = Math.abs(this.getTheta() - other.getTheta()) < MAX_DELTA;
        boolean radius_equal = Math.abs(this.getRadius() - other.getRadius()) < MAX_DELTA;
        return phi_equal && theta_equal && radius_equal;
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        asCartesianCoordinate().writeOn(rset);
    }

    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }

    public String getIdAsString() {
        return null;
    }

    //uses Cartesian Coordinate to avoid redundant database information
    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        CartesianCoordinate cartesianCoordinate = asCartesianCoordinate();
        cartesianCoordinate.readFrom(rset);
        SphericCoordinate copy = cartesianCoordinate.asSphericCoordinate();
        radius = copy.getRadius();
        phi = copy.getPhi();
        theta = copy.getTheta();
    }

    @Override
    public double getDistance(Coordinate coordinate) {
        return asCartesianCoordinate().getDistance(coordinate);
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
        if (!(coordinate instanceof SphericCoordinate)){
            return false;
        }
        //only if they differ, the individual coordinates are being compared in another method
        return allValuesIdetical((SphericCoordinate) coordinate);
    }

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
        return Objects.hash(phi,theta,radius);
    }
}
