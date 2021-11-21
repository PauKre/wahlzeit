package org.wahlzeit.model.coordinate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateConversionTest {
    @Test
    public void testConversion1(){
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(12,8,15);
        SphericCoordinate s1 =  cartesianCoordinate.asSphericCoordinate();
        assertEquals(s1.asCartesianCoordinate(), cartesianCoordinate);
    }



}
