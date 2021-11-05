package org.wahlzeit.model;

import org.junit.Test;

public class CoodinateTest {
    
    //making use of 3**2 + 4**2 = 5**2
    @Test
    public void testDistanceCalculation1(){
        Coordinate c1 = new Coordinate(5,10, 15);
        Coordinate c2 = new Coordinate(8,6, 15);
        double diff = c1.getDistance(c2);
        assert (diff == 5);
    }

    @Test
    public void testDistanceCalculation2(){
        Coordinate c1 = new Coordinate(7,5, 15);
        Coordinate c2 = new Coordinate(7,8, 11);
        double diff = c1.getDistance(c2);
        assert (diff == 5);
    }

    @Test
    public void testDistanceCalculation3(){
        Coordinate c1 = new Coordinate(-7,12, -2);
        Coordinate c2 = new Coordinate(-3,12, 1);
        double diff = c1.getDistance(c2);
        assert (diff == 5);
    }

    @Test
    public void testIsEqual(){
        Coordinate c1 = new Coordinate(7,5, 15);
        Coordinate c2 = new Coordinate(7,8, 11);
        double diff = c1.getDistance(c2);
        assert (diff == 5);
    }



}
