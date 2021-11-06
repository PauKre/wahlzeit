package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    //test with positive coordinates
    @Test
    public void testDistanceCalculation1(){
        Coordinate c1 = new Coordinate(5,10, 15);
        Coordinate c2 = new Coordinate(8,6, 25);
        assertEquals(c1.getDistance(c2),11.2, 0.1);
    }

    //test with partly negative Coordinates
    @Test
    public void testDistanceCalculation2(){
        Coordinate c1 = new Coordinate(4,-3, 11);
        Coordinate c2 = new Coordinate(-6,-7, 8);
        assertEquals(c1.getDistance(c2),11.2, 0.1);
    }

    //Test with negative coordinates
    @Test
    public void testDistanceCalculation3(){
        Coordinate c1 = new Coordinate(-7,-12, -2);
        Coordinate c2 = new Coordinate(-3,-9, -12);
        double diff = c1.getDistance(c2);
        assertEquals(diff,11.2, 0.1);
    }

    //Test distance to self
    @Test
    public void testDistanceCalculation4(){
        Coordinate c1 = new Coordinate(-7,12, -2);
        double diff = c1.getDistance(c1);
        assertEquals (diff , 0, 0);
    }

    //Test Constructor and Getter/Setter functionality
    @Test
    public void testCoreFunctions(){
        //Test Constructor
        Coordinate c1 = new Coordinate(1,-2,3.6);
        assertEquals(c1.getX(), 1, 0);
        assertEquals(c1.getY(), -2, 0);
        assertEquals(c1.getZ(), 3.6, 0);
        //Test Getter and Setter
        c1.setX(-1.234);
        c1.setY(4.6);
        c1.setZ(3);
        assertEquals(c1.getX(), -1.234, 0);
        assertEquals(c1.getY(), 4.6, 0);
        assertEquals(c1.getZ(), 3, 0);
    }


    //Test Equal with completely different, partly different and same coordinates
    @Test
    public void testIsEqual1(){
        Coordinate c1 = new Coordinate(7,5, 15);
        Coordinate c2 = new Coordinate(8,4, 16);
        assertFalse(c1.equals(c2));
        c2.setX(7);
        c2.setY(5);
        assertFalse(c1.equals(c2));
        c2.setZ(15);
        assertTrue(c1.equals(c2));
    }

    //Test with equal coordinates initialized
    @Test
    public void testIsEqual2() {
        Coordinate c1 = new Coordinate(7, 5, 15);
        Coordinate c2 = new Coordinate(7, 5, 15);
        assertTrue(c1.equals(c2));
    }

    //Test with null
    @Test
    public void testIsEqual3() {
        Coordinate c1 = new Coordinate(7, 5, 15);
        assertFalse(c1.equals(null));
    }

    //Test with different object
    @Test
    public void testIsEqual4() {
        Coordinate c1 = new Coordinate(7, 5, 15);
        Coordinate c2 = new Coordinate(7, 5, 15);
        assertFalse(c1.equals(new Location(c2)));
    }

    //Test with self
    @Test
    public void testIsEqual5() {
        Coordinate c1 = new Coordinate(7, 5, 15);
        assertTrue(c1.equals(c1));
    }


}
