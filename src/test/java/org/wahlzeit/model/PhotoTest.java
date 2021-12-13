package org.wahlzeit.model;

import org.junit.Test;
import org.wahlzeit.model.coordinate.CartesianCoordinate;
import org.wahlzeit.model.coordinate.CoordinateException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhotoTest {

    @Test
    public void testConstructor(){
        Photo photo = new Photo();
        assertNotNull(photo);
    }
    @Test
    public void testSetterAndGetter() throws CoordinateException {
        Photo photo = new Photo();
        photo.setLocation(new Location(new CartesianCoordinate(1,-2,3.4567)));
        CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) photo.getLocation().getCoordinate();
        assertEquals(cartesianCoordinate.getX(), 1, 0);
        assertEquals(cartesianCoordinate.getY(), -2, 0);
        assertEquals(cartesianCoordinate.getZ(), 3.4567, 0);
    }

}
