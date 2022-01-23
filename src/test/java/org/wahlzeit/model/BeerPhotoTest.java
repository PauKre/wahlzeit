package org.wahlzeit.model;

import org.junit.Test;
import org.wahlzeit.model.beer.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeerPhotoTest {

    //test the constructor
    @Test
    public void testCoreFunctionality() throws BeerTypeException {
        BeerPhotoFactory factory = BeerPhotoFactory.getInstance();
        assertNotNull(factory);
        //Factory call
        BeerPhoto photo = factory.createPhoto();
        BeerManager beerManager = BeerManager.getInstance();
        Beer kitzmannEdelpils = beerManager.createBeer("Pils", "Erlangen" , 11.2, 1712, 5.0);
        photo.setBeer(kitzmannEdelpils);
        Beer beer = photo.getBeer();
        //Kitzmann Edelpils

        assertEquals(beer.getCityOfOrigin(), "Erlangen");
        assertEquals(beer.getOriginalWort(), 11.2, 0.0);
        assertEquals(beer.getYearEstablished(), 1712, 0.0);
        assertEquals(beer.getAlcoholicStrength(), 5.0, 0.0);
    }


}
