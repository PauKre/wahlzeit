package org.wahlzeit.model;

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
}
