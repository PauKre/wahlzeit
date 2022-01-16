package org.wahlzeit.model;

import org.junit.Test;
import org.wahlzeit.model.beer.Beer;
import org.wahlzeit.model.beer.BeerPhoto;
import org.wahlzeit.model.beer.BeerPhotoFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeerPhotoTest {

    //test the constructor
    @Test
    public void testCoreFunctionality() {
        BeerPhotoFactory factory = BeerPhotoFactory.getInstance();
        assertNotNull(factory);
        BeerPhoto photo = factory.createPhoto();
        Beer beer = photo.getBeer();
        //Kitzmann Edelpils
        beer.setCityOfOrigin("Erlangen");
        beer.setOriginalWort(11.2);
        beer.setYearEstablished(1712);
        beer.setAlcoholicStrength(5.0);

        assertEquals(beer.getCityOfOrigin(), "Erlangen");
        assertEquals(beer.getOriginalWort(), 11.2, 0.0);
        assertEquals(beer.getYearEstablished(), 1712, 0.0);
        assertEquals(beer.getAlcoholicStrength(), 5.0, 0.0);
    }


}
