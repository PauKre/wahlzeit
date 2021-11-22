package org.wahlzeit.model;

import org.junit.Test;
import org.wahlzeit.model.coordinate.CartesianCoordinate;
import org.wahlzeit.model.coordinate.SphericCoordinate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocationTest {

    //test the constructor
    @Test
    public void testConstructorWithCartesianCoordinate(){
        Location location = new Location(new CartesianCoordinate(1,-0.5, 3.14));
        assertNotNull(location);
        assertNotNull(location.getCoordinate());
        CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) location.getCoordinate();
        assertEquals(cartesianCoordinate.getX(), 1, 0);
        assertEquals(cartesianCoordinate.getY(), -0.5, 0);
        assertEquals(cartesianCoordinate.getZ(), 3.14, 0);
    }

    @Test
    public void testConstructorWithSphericCoordinate(){
        Location location = new Location(new SphericCoordinate(1,-0.5, 3.14));
        assertNotNull(location);
        assertNotNull(location.getCoordinate());
        SphericCoordinate sphericCoordinate = (SphericCoordinate) location.getCoordinate();
        assertEquals(sphericCoordinate.getPhi(), 1, 0);
        assertEquals(sphericCoordinate.getTheta(), -0.5, 0);
        assertEquals(sphericCoordinate.getRadius(), 3.14, 0);
    }

    //Test Setter and Getter
    @Test
    public void testSetterAndGetter(){
        Location location = new Location(new CartesianCoordinate(-3, 9.81, 42));
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1, -1, 2.5);
        location.setCoordinate(cartesianCoordinate);
        CartesianCoordinate newCartesianCoordinate = (CartesianCoordinate) location.getCoordinate();
        assertEquals(newCartesianCoordinate.getX(), 1, 0);
        assertEquals(newCartesianCoordinate.getY(), -1, 0);
        assertEquals(newCartesianCoordinate.getZ(), 2.5, 0);
    }

}
