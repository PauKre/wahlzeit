package org.wahlzeit.model.beer;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeerPhoto extends Photo {


    //5 new attributes are being added to the BeerPhoto
    protected String cityOfOrigin;
    protected double originalWort;
    protected int yearEstablished;
    protected double alcoholicStrength;
    protected BeerStyle beerStyle;



//    The readFrom method calls super, then reads the BeerPhoto attributes from the resultSet
    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        cityOfOrigin = rset.getString("cityOfOrigin");
        originalWort = rset.getDouble("originalWort");
        yearEstablished = rset.getInt("yearEstablished");
        alcoholicStrength = rset.getDouble("alcoholicStrength");
        beerStyle = BeerStyle.getByKey(rset.getString("beerStyle"));
    }

    //    The writeOn method calls super, then updates the BeerPhoto attributes in the resultSet
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateString("cityOfOrigin", cityOfOrigin);
        rset.updateDouble("originalWort", originalWort);
        rset.updateInt("yearEstablished", yearEstablished);
        rset.updateDouble("alcoholicStrength", alcoholicStrength);
        rset.updateString("beerStyle", beerStyle.getKey());
    }

    //the 2 simple constructoers just call super
    public BeerPhoto(){
        super();
    }

    public BeerPhoto(PhotoId myId){
        super(myId);
    }

    //The constructor with the ResultSet as Parameter calls readFrom, which calls super itself
    public BeerPhoto(ResultSet rset) throws SQLException {
        readFrom(rset);
    }

    //The getters are standard java convention
    public String getCityOfOrigin() {
        return cityOfOrigin;
    }

    //The setters call incWriteCount in the super class in addition to their setter functionality
    public void setCityOfOrigin(String cityOfOrigin) {
        this.cityOfOrigin = cityOfOrigin;
        incWriteCount();
    }

    public double getOriginalWort() {
        return originalWort;
    }

    public void setOriginalWort(double originalWort) {
        this.originalWort = originalWort;
        incWriteCount();
    }

    public int getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(int yearEstablished) {
        this.yearEstablished = yearEstablished;
        incWriteCount();
    }

    public double getAlcoholicStrength() {
        return alcoholicStrength;
    }

    public void setAlcoholicStrength(double alcoholicStrength) {
        this.alcoholicStrength = alcoholicStrength;
        incWriteCount();
    }

    public BeerStyle getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(BeerStyle beerStyle) {
        this.beerStyle = beerStyle;
        incWriteCount();
    }
}
