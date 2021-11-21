package org.wahlzeit.model.coordinate;

import org.junit.Test;
import org.wahlzeit.model.Location;

import static org.junit.Assert.*;

public class SphericCoordinateTest {

    //test with positive coordinates
    @Test
    public void testDistanceCalculation1(){
        SphericCoordinate c1 = new SphericCoordinate(90,0, 15);
        SphericCoordinate c2 = new SphericCoordinate(-90,0, 15);
        assertEquals(30,c1.getDistance(c2), 0.1);
    }

    //test with partly negative Coordinates
    @Test
    public void testDistanceCalculation2(){
        SphericCoordinate c1 = new SphericCoordinate(4,-3, 11);
        SphericCoordinate c2 = new SphericCoordinate(-6,-7, 8);
        assertEquals(c1.getDistance(c2),11.2, 0.1);
    }

    //Test with negative coordinates
    @Test
    public void testDistanceCalculation3(){
        SphericCoordinate c1 = new SphericCoordinate(-7,-12, -2);
        SphericCoordinate c2 = new SphericCoordinate(-3,-9, -12);
        double diff = c1.getDistance(c2);
        assertEquals(diff,11.2, 0.1);
    }

    //Test distance to self
    @Test
    public void testDistanceCalculation4(){
        SphericCoordinate c1 = new SphericCoordinate(-7,12, -2);
        double diff = c1.getDistance(c1);
        assertEquals (diff , 0, 0);
    }

    //Test Constructor and Getter/Setter functionality
    @Test
    public void testCoreFunctions(){
        //Test Constructor
        SphericCoordinate c1 = new SphericCoordinate(1,-2,3.6);
        assertEquals(c1.getPhi(), 1, 0);
        assertEquals(c1.getTheta(), -2, 0);
        assertEquals(c1.getRadius(), 3.6, 0);
        //Test Getter and Setter
        c1.setPhi(-1.234);
        c1.setTheta(4.6);
        c1.setRadius(3);
        assertEquals(c1.getPhi(), -1.234, 0);
        assertEquals(c1.getTheta(), 4.6, 0);
        assertEquals(c1.getRadius(), 3, 0);
    }


    //Test Equal with completely different, partly different and same coordinates
    @Test
    public void testIsEqual1(){
        SphericCoordinate c1 = new SphericCoordinate(7,5, 15);
        SphericCoordinate c2 = new SphericCoordinate(8,4, 16);
        assertFalse(c1.isEqual(c2));
        c2.setPhi(7);
        c2.setTheta(5);
        assertFalse(c1.isEqual(c2));
        c2.setRadius(15);
        assertTrue(c1.isEqual(c2));
    }

    //Test with equal coordinates initialized
    @Test
    public void testIsEqual2() {
        SphericCoordinate c1 = new SphericCoordinate(7, 5, 15);
        SphericCoordinate c2 = new SphericCoordinate(7, 5, 15);
        assertTrue(c1.equals(c2));
    }

    //Test with null
    @Test
    public void testIsEqual3() {
        SphericCoordinate c1 = new SphericCoordinate(7, 5, 15);
        assertFalse(c1.equals(null));
    }

    //Test with different object
    @Test
    public void testIsEqual4() {
        SphericCoordinate c1 = new SphericCoordinate(7, 5, 15);
        SphericCoordinate c2 = new SphericCoordinate(7, 5, 15);
        assertFalse(c1.equals(new Location(c2)));
    }

    //Test with self
    @Test
    public void testIsEqual5() {
        SphericCoordinate c1 = new SphericCoordinate(7, 5, 15);
        assertTrue(c1.equals(c1));
    }

}
