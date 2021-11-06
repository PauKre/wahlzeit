package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocationTest {

    //test the constructor
    @Test
    public void testConstructor(){
        Location location = new Location(new Coordinate(1,-0.5, 3.14));
        assertNotNull(location);
        assertNotNull(location.getCoordinate());
        assertEquals(location.getCoordinate().getX(), 1, 0);
        assertEquals(location.getCoordinate().getY(), -0.5, 0);
        assertEquals(location.getCoordinate().getZ(), 3.14, 0);
    }

    //Test Setter and Getter
    @Test
    public void testSetterAndGetter(){
        Location location = new Location(new Coordinate(-3, 9.81, 42));
        Coordinate coordinate = new Coordinate(1, -1, 2.5);
        location.setCoordinate(coordinate);
        assertEquals(location.getCoordinate().getX(), 1, 0);
        assertEquals(location.getCoordinate().getY(), -1, 0);
        assertEquals(location.getCoordinate().getZ(), 2.5, 0);
    }

}
