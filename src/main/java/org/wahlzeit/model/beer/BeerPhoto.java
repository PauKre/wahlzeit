package org.wahlzeit.model.beer;

import org.wahlzeit.model.Photo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeerPhoto extends Photo {

    public static final String CITY_OF_ORIGIN = "cityOfOrigin";
    public static final String ORIGINAL_WORT = "originalWort";
    public static final String YEAR_ESTABLISHED = "yearEstablished";
    public static final String ALCOHOLIC_STRENGTH = "alcoholicStrength";
    public static final String BEER_STYLE = "beer_style"; //e.g. https://hobbybrauer.de/forum/wiki/doku.php/biertypen


    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);

    }
}
