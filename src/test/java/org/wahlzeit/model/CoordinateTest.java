package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    //making use of 3**2 + 4**2 = 5**2
    @Test
    public void testDistanceCalculation1(){
        Coordinate c1 = new Coordinate(5,10, 15);
        Coordinate c2 = new Coordinate(8,6, 15);
        double diff = c1.getDistance(c2);
        assertEquals(diff,5, 0);
    }

    @Test
    public void testDistanceCalculation2(){
        Coordinate c1 = new Coordinate(7,5, 15);
        Coordinate c2 = new Coordinate(7,8, 11);
        double diff = c1.getDistance(c2);
        assertEquals(diff,5, 0);
    }

    @Test
    public void testDistanceCalculation3(){
        Coordinate c1 = new Coordinate(-7,12, -2);
        Coordinate c2 = new Coordinate(-3,12, 1);
        double diff = c1.getDistance(c2);
        assertEquals(diff,5, 0);
    }

    @Test
    public void testDistanceCalculation4(){
        Coordinate c1 = new Coordinate(-7,12, -2);
        Coordinate c2 = new Coordinate(-7,12, -2);
        double diff = c1.getDistance(c2);
        assertEquals (diff , 0, 0);
    }

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

    @Test
    public void testIsEqual2() {
        Coordinate c1 = new Coordinate(7, 5, 15);
        Coordinate c2 = new Coordinate(7, 5, 15);
        assertTrue(c1.equals(c2));
    }

    @Test
    public void testIsEqual3() {
        Coordinate c1 = new Coordinate(7, 5, 15);
        assertFalse(c1.equals(null));
    }

    @Test
    public void testIsEqual4() {
        Coordinate c1 = new Coordinate(7, 5, 15);
        Coordinate c2 = new Coordinate(7, 5, 15);
        assertFalse(c1.equals(new Location(c2)));
    }

    @Test
    public void testIsEqual5() {
        Coordinate c1 = new Coordinate(7, 5, 15);
        assertTrue(c1.equals(c1));
    }


}
