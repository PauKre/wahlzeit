package org.wahlzeit.model.coordinate;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateConversionTest {

    @Test
    public void testConversion1() throws CoordinateException {
        CartesianCoordinate c1 = new CartesianCoordinate(12,8,15);
        assertNotEquals(c1.asSphericCoordinate(), c1);
        assertEquals(c1.asSphericCoordinate().asCartesianCoordinate(), c1);
        assertEquals(c1.asSphericCoordinate().asCartesianCoordinate().asSphericCoordinate(), c1.asSphericCoordinate());
    }

    @Test
    public void testConversion2() throws CoordinateException {
        SphericCoordinate s1 =  new SphericCoordinate(Math.PI/3, 1.2* Math.PI, 10);
        assertNotEquals(s1.asCartesianCoordinate(), s1);
        assertEquals(s1.asCartesianCoordinate().asSphericCoordinate(), s1);
        assertEquals(s1.asCartesianCoordinate().asSphericCoordinate().asCartesianCoordinate(), s1.asCartesianCoordinate());
    }

    @Test
    public void testDistance1(){

    }



}
