package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhotoTest {

    @Test
    public void testConstructor(){
        Photo photo = new Photo();
        assertNotNull(photo);
    }
    @Test
    public void testSetterAndGetter(){
        Photo photo = new Photo();
        photo.setLocation(new Location(new Coordinate(1,-2,3.4567)));
        Coordinate coordinate = photo.getLocation().getCoordinate();
        assertEquals(coordinate.getX(), 1, 0);
        assertEquals(coordinate.getY(), -2, 0);
        assertEquals(coordinate.getZ(), 3.4567, 0);
    }

}
