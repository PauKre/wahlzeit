package org.wahlzeit.model.coordinate;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.PatternInstance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

@PatternInstance(
        patternName = "Template Method",
        participants = {
                "Concrete Class"
        }
)
public class SphericCoordinate extends AbstractCoordinate{

    private static final HashMap<Integer, SphericCoordinate> valueObjects = new HashMap<>();

    private double phi;

    private double theta;

    private double radius;

    double MAX_DELTA = 0.00001;

    public SphericCoordinate(double phi, double theta, double radius) throws CoordinateException{
        delete();
        this.phi = cleanAngle(phi);
        this.theta = cleanAngle(theta);
        this.radius = radius;
        add();
        assertClassInvariants();
    }

    //converts angles to values between 0 and 2 Pi
    private double cleanAngle(double angle) {
        //solved modulo bug
        return ((angle % (2 * Math.PI))+ (2 * Math.PI)) % (2 * Math.PI);
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        delete();
        this.phi = cleanAngle(phi);
        add();
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        delete();
        this.theta = cleanAngle(theta);
        add();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        delete();
        this.radius = radius;
        add();
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() throws CoordinateException {
        assertClassInvariants();
        double x = radius * Math.sin(phi) * Math.cos(theta);
        double y = radius * Math.sin(phi) * Math.sin(theta);
        double z = radius * Math.cos(phi);
        return new CartesianCoordinate(x, y, z);
    }




    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
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


    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }

    @Override
    public void assertClassInvariants() throws CoordinateException {
        if(!(0 <= theta && theta <= (2 * Math.PI) && 0 <= phi && phi <= (2 * Math.PI)
                && MAX_DELTA < 0.1)){
            throw new CoordinateException("SphericCoordinate is not valid");
        }
    }

    public String getIdAsString() {
        return null;
    }

    //uses Cartesian Coordinate to avoid redundant database information
    @Override
    public void readFrom(ResultSet rset) throws SQLException{
        try {
            assertClassInvariants();
            delete();
            SphericCoordinate coordinate = valueObjects.get(rset.getInt("coordinate_hash")).asSphericCoordinate();
            radius = coordinate.getRadius();
            phi = coordinate.getPhi();
            theta = coordinate.getTheta();
            assertClassInvariants();
            add();
        } catch (CoordinateException coordinateException) {
            coordinateException.printStackTrace();
        }
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
    public int hashCode() {
        return Objects.hash(phi,theta,radius);
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) throws CoordinateException {
        SphericCoordinate other = coordinate.asSphericCoordinate();
        try {
            return(Math.acos(Math.sin(this.getTheta())*Math.sin(other.getTheta()) + Math.cos(this.getTheta())*Math.cos(other.getTheta())*Math.cos(this.getPhi()-other.getPhi())));
        }catch (Exception e){
            throw new ArithmeticException("Error in Calculating the Central Angle!");
        }
    }

    public void delete(){
        valueObjects.remove(hashCode());
    }

    private void add() {
        valueObjects.put(hashCode(), this);
    }
}
