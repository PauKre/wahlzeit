package org.wahlzeit.model.coordinate;

import org.junit.Test;
import org.wahlzeit.model.Location;

import static org.junit.Assert.*;

public class SphericCoordinateTest {

    //test for points opposite of each other on the sphere
    @Test
    public void testDistanceCalculation1() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(Math.PI/2,0, 15);
        SphericCoordinate c2 = new SphericCoordinate(Math.PI + Math.PI/2,0, 15);
        assertEquals(30,c1.getDistance(c2), 0.1);
    }

    //test with random values
    @Test
    public void testDistanceCalculation2() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(0.4266,-0.6435, 12.0830);
        SphericCoordinate c2 = new SphericCoordinate(0.8561,-2.2794, 12.2066);
        assertEquals(c1.getDistance(c2),11.2, 0.1);
    }

    //Test with more random values
    @Test
    public void testDistanceCalculation3() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(1.7138,-2.0989, 14.0457);
        SphericCoordinate c2 = new SphericCoordinate(2.4726,-1.8925, 15.2971);
        double diff = c1.getDistance(c2);
        assertEquals(diff,11.2, 0.1);
    }

    //Test distance to self
    @Test
    public void testDistanceCalculation4() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(-7,12, -2);
        double diff = c1.getDistance(c1);
        assertEquals (diff , 0, 0);
    }

    //Test distance to null
    @Test(expected = IllegalArgumentException.class)
    public void testDistanceCalculation5() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(-7,12, -2);
        double diff = c1.getDistance(null);
    }

    //Test Constructor and Getter/Setter functionality
    @Test
    public void testCoreFunctions() throws CoordinateException {
        //Test Constructor
        SphericCoordinate c1 = new SphericCoordinate(1,-2,3.6);
        assertEquals(c1.getPhi(), 1, 0);
        assertEquals(c1.getTheta(), 4.2832, 0.001);
        assertEquals(c1.getRadius(), 3.6, 0);
        //Test Getter and Setter
        c1.setPhi(-1.234);
        c1.setTheta(4.6);
        c1.setRadius(3);
        //note that phi is cleaned and therefore changes
        assertEquals(c1.getPhi(), 5.0492, 0.001);
        assertEquals(c1.getTheta(), 4.6, 0);
        assertEquals(c1.getRadius(), 3, 0);
    }


    //Test Equal with completely different, partly different and same values
    @Test
    public void testIsEqual1() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(7,5, 15);
        SphericCoordinate c2 = new SphericCoordinate(8,4, 16);
        assertFalse(c1.isEqual(c2));
        c2.setPhi(7);
        c2.setTheta(5);
        assertFalse(c1.isEqual(c2));
        c2.setRadius(15);
        assertTrue(c1.isEqual(c2));
    }

    //Test with equal values initialized
    @Test
    public void testIsEqual2() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(7, 5, 15);
        SphericCoordinate c2 = new SphericCoordinate(7, 5, 15);
        assertTrue(c1.equals(c2));
    }

    //Test with null
    @Test
    public void testIsEqual3() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(7, 5, 15);
        assertFalse(c1.equals(null));
    }

    //Test with different object
    @Test
    public void testIsEqual4() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(7, 5, 15);
        SphericCoordinate c2 = new SphericCoordinate(7, 5, 15);
        assertFalse(c1.equals(new Location(c2)));
    }

    //Test with self
    @Test
    public void testIsEqual5() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(7, 5, 15);
        assertTrue(c1.equals(c1));
    }

    //test clean angle functionality
    @Test
    public void testIsEqual6() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(356.46523, 3452.453, 15);
        SphericCoordinate c2 = new SphericCoordinate(Math.PI * 4 + 356.46523, Math.PI * -16 + 3452.453, 15);
        assertTrue(c1.equals(c2));
    }

    //Test
    @Test
    public void testIsEqual7() throws CoordinateException {
        SphericCoordinate c1 = new SphericCoordinate(356.46523, 3452.453, 15);
        SphericCoordinate c2 = new SphericCoordinate(Math.PI * 4 + 356.46523, Math.PI * -16 + 3452.453, 15);
        assertTrue(c1.equals(c2));
    }

    }
