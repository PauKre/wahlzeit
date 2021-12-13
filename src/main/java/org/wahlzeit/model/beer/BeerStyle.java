package org.wahlzeit.model.beer;

import java.security.InvalidParameterException;

//This enum manages the different BeerStyles
public enum BeerStyle {

    //The different values are initialized
    //A string parameter is given in the constructor to
    LAGER("lager"),
    PILSENER("pilsener"),
    BOCK("bock"),
    WEIZEN("weizen"),
    UNKNOWN("unknown");

    //the key variable is used to identify the Enum values
    private final String key;

    //the constructor sets the given argument to the enum variable
    BeerStyle(String key){
        this.key = key;
    }

    //Helper Method to get the key for a specific BeerStyle
    public String getKey() {
        return key;
    }

    //static helper method to get the BeerStyle by key
    public static BeerStyle getByKey(String key){
        for (BeerStyle beerStyle : BeerStyle.values()) {
            if(beerStyle.getKey().equals(key)){
                return beerStyle;
            }
        }
        //if no BeerStyle for the specific key is found, an IllegalArgumentException is thrown
        throw new IllegalArgumentException("not a valid key");
    }
}
