package org.wahlzeit.model;

public class Coordinate {

    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getDistance(Coordinate other){
        double x_delta_squared = Math.pow(this.getX()-other.getX(), 2);
        double y_delta_squared = Math.pow(this.getY()-other.getY(), 2);
        double z_delta_squared = Math.pow(this.getZ()-other.getZ(), 2);
        return Math.sqrt(x_delta_squared+y_delta_squared+z_delta_squared);
    }

    public boolean isEqual(Object other){
        if(other == null || other.getClass().equals(Coordinate.class)){
            return false;
        }
        if (this == other || allCoordinatesIdentical((Coordinate) other)){
            return true;
        }
        return false;
    }

    private boolean allCoordinatesIdentical(Coordinate other) {
        return this.getX() == other.getX() && this.getY() == other.getY() && this.getZ() == other.getZ();
    }

    @Override
    public boolean equals(Object obj) {
        return isEqual(obj);
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


