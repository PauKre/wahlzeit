package org.wahlzeit.model;

public class Coordinate {

    private double x;
    private double y;
    private double z;

    //Parameter constructor
    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //calculates cartesian distance
    public double getDistance(Coordinate other){
        //No nullcheck is provides, as the caller should make sure that the other object is valid
        //the calculation is split into calculating each summand...
        double x_delta_squared = Math.pow(this.getX()-other.getX(), 2);
        double y_delta_squared = Math.pow(this.getY()-other.getY(), 2);
        double z_delta_squared = Math.pow(this.getZ()-other.getZ(), 2);
        //and taking the square root of the sum
        return Math.sqrt(x_delta_squared+y_delta_squared+z_delta_squared);
    }

    public boolean isEqual(Coordinate other){
        //for better performance, it is checked if the objects are the same, first
        if (this == other){
            return true;
        }
        //only if they differ, the individual coordinates are being compared in another method
        return allCoordinatesIdentical(other);
    }

    //this helper method compares the individual coordinates of two coordinate instances
    private boolean allCoordinatesIdentical(Coordinate other) {
        return this.getX() == other.getX() && this.getY() == other.getY() && this.getZ() == other.getZ();
    }

    //the equals method is overridden as specified.
    @Override
    public boolean equals(Object obj) {
        //equals first checks for null and objects of other classes than Coordinate
        if(obj == null || !obj.getClass().equals(Coordinate.class)){
            return false;
        }
        return isEqual((Coordinate) obj);
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
}


