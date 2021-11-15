package org.wahlzeit.model;

import org.junit.Test;
import org.wahlzeit.model.beer.BeerPhoto;
import org.wahlzeit.model.beer.BeerPhotoFactory;
import org.wahlzeit.model.beer.BeerStyle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeerPhotoTest {

    //test the constructor
    @Test
    public void testCoreFunctionality(){
        BeerPhotoFactory factory = BeerPhotoFactory.getInstance();
        assertNotNull(factory);
        BeerPhoto photo = factory.createPhoto();
        //Kitzmann Edelpils
        photo.setCityOfOrigin("Erlangen");
        photo.setOriginalWort(11.2);
        photo.setYearEstablished(1712);
        photo.setAlcoholicStrength(5.0);
        photo.setBeerStyle(BeerStyle.PILSENER);
        assertEquals(photo.getCityOfOrigin(), "Erlangen");
        assertEquals(photo.getOriginalWort(), 11.2, 0.0);
        assertEquals(photo.getYearEstablished(), 1712, 0.0);
        assertEquals(photo.getAlcoholicStrength(), 5.0, 0.0);
        assertEquals(photo.getBeerStyle(), BeerStyle.PILSENER);

    }

}
