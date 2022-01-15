package org.wahlzeit.model.beer;

import java.util.HashMap;
import java.util.Map;

public class BeerManager {

    private Map<String, Beer> beers = new HashMap<>();
    private HashMap<String, BeerType> beerTypes = new HashMap<>();

    public Beer createBeer(String beerTypeName){
        BeerType beerType = getBeerType(beerTypeName);
        Beer beer = beerType.createInstance();
        beers.put(beer.getIdAsString(), beer);
        return beer;
    }

    private BeerType getBeerType(String beerTypeName) {
        BeerType beerType = beerTypes.get(beerTypeName);
        if(beerType == null){
            beerType = new BeerType(beerTypeName);
            beerTypes.put(beerTypeName, beerType);
        }
        return beerType;

    }
}
