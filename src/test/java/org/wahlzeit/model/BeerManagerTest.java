package org.wahlzeit.model;

import org.junit.Test;
import org.wahlzeit.model.beer.*;

import static org.junit.Assert.*;

public class BeerManagerTest {

    //test the constructor
    @Test
    public void testCoreFunctionality() throws BeerTypeException {
        BeerManager beerManager = BeerManager.getInstance();
        Beer kitzmann = beerManager.createBeer("Pils", "Erlangen", 11.2, 1712, 5.0);
        Beer hofmann = beerManager.createBeer("Pils", "Pahres", 11.6, 1663, 4.9);
        Beer sternla = beerManager.createBeer("Hell", "Wuerzburg", 11.3, 1643, 4.9);
        Beer storchenbier = beerManager.createBeer("Hell", "Erlangen", 12.5, 1861, 5.2);

        assertEquals(sternla.getType(), storchenbier.getType());
        assertNotEquals(sternla.getType(), kitzmann.getType());
        assertEquals(hofmann.getType(), kitzmann.getType());
        assertNotEquals(hofmann.getType(), storchenbier.getType());

    }

    @Test(expected = BeerTypeException.class)
    public void testBeerTypeCreation() throws BeerTypeException {
        BeerManager beerManager = BeerManager.getInstance();
        BeerType vollbier = beerManager.addBeerType("Vollbier", false);
        BeerType hellesVollbier = beerManager.addBeerType("Helles Vollbier", true);
        vollbier.addSubType(hellesVollbier);
        BeerType subTypeVollbier = beerManager.addBeerType("Vollbier", true);
    }

    @Test(expected = BeerTypeException.class)
    public void testInstanciatingNonSubTypeBeer() throws BeerTypeException {
        BeerManager beerManager = BeerManager.getInstance();
        BeerType vollbier = beerManager.addBeerType("Vollbier", false);
        Beer spalter = beerManager.createBeer("Vollbier", "Spalt", 11.7, 1879, 4.8);
    }

    @Test
    public void testSubTypeLogic() throws BeerTypeException {
        BeerManager beerManager = BeerManager.getInstance();
        BeerType vollbier = beerManager.addBeerType("Vollbier", false);
        BeerType hellesVollbier = beerManager.addBeerType("Helles Vollbier", true);
        vollbier.addSubType(hellesVollbier);


        Beer spalter = beerManager.createBeer("Helles Vollbier", "Spalt", 11.7, 1879, 4.8);
        assertEquals(spalter.getType().getSuperType(), vollbier);
        assertEquals(vollbier.getSubTypeIterator().next(), hellesVollbier);
        assertEquals(vollbier.getSuperType(), null);
    }
}
