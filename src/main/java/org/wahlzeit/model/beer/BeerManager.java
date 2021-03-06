package org.wahlzeit.model.beer;

import org.wahlzeit.model.PhotoManager;

import java.util.HashMap;
import java.util.Map;

public class BeerManager {

    protected static final BeerManager instance = new BeerManager();

    public static final BeerManager getInstance(){
        return instance;
    }

    private BeerManager(){};

    private Map<String, Beer> beers = new HashMap<>();
    private HashMap<String, BeerType> beerTypes = new HashMap<>();

    public Beer createBeer(String beerTypeName, String cityOfOrigin, double originalWort, int yearEstablished, double alcoholicStrength) throws BeerTypeException {
        BeerType beerType = getBeerType(beerTypeName);
        if(!beerType.isSubtype()){
            throw new BeerTypeException("the beer type " + beerTypeName + " is not a subtype");
        }
        //BeerType call
        Beer beer = beerType.createInstance(cityOfOrigin, originalWort, yearEstablished, alcoholicStrength);
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

    public BeerType addBeerType(String beerTypeName, boolean isSubtype) throws BeerTypeException {
        BeerType beerType = beerTypes.get(beerTypeName);
        if(beerType != null){
            if(beerType.isSubtype() != isSubtype){
                throw new BeerTypeException("Beer type is already present with different isSubtype value!");
            }
        }else {
            beerType = new BeerType(beerTypeName, isSubtype);
            beerTypes.put(beerTypeName, beerType);
        }
        return beerType;
    }
}
