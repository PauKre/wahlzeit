package org.wahlzeit.model.beer;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeerPhoto extends Photo {

    public static final String CITY_OF_ORIGIN = "cityOfOrigin";
    public static final String ORIGINAL_WORT = "originalWort";
    public static final String YEAR_ESTABLISHED = "yearEstablished";
    public static final String ALCOHOLIC_STRENGTH = "alcoholicStrength";
    public static final String BEER_STYLE = "beer_style"; //e.g. https://hobbybrauer.de/forum/wiki/doku.php/biertypen

    protected String cityOfOrigin;
    protected double originalWort;
    protected int yearEstablished;
    protected double alcoholicStrength;
    protected BeerStyle beerStyle;



    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        cityOfOrigin = rset.getString(CITY_OF_ORIGIN);
        originalWort = rset.getDouble(ORIGINAL_WORT);
        yearEstablished = rset.getInt(YEAR_ESTABLISHED);
        alcoholicStrength = rset.getDouble(ALCOHOLIC_STRENGTH);
        beerStyle = BeerStyle.getByKey(rset.getString(BEER_STYLE));
        super.readFrom(rset);
    }


    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateString(CITY_OF_ORIGIN, cityOfOrigin);
        rset.updateDouble(ORIGINAL_WORT, originalWort);
        rset.updateInt(YEAR_ESTABLISHED, yearEstablished);
        rset.updateDouble(ALCOHOLIC_STRENGTH, alcoholicStrength);
        rset.updateString(BEER_STYLE, beerStyle.getKey());
        super.writeOn(rset);
    }

    public BeerPhoto(PhotoId myId){
        super(myId);
    }

    public BeerPhoto(ResultSet rset) throws SQLException {
        readFrom(rset);
    }
}
