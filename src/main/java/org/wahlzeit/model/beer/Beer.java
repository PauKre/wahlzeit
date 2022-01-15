package org.wahlzeit.model.beer;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Beer extends DataObject {

    protected BeerType beerType = null;


    //moved from BeerPhoto
    protected String cityOfOrigin;
    protected double originalWort;
    protected int yearEstablished;
    protected double alcoholicStrength;





    public Beer(BeerType beerType) {
        this.beerType = beerType;
    }


    public BeerType getBeerType() {
        return beerType;
    }

    public void setBeerType(BeerType beerType) {
        this.beerType = beerType;
    }

    public String getCityOfOrigin() {
        return cityOfOrigin;
    }

    public void setCityOfOrigin(String cityOfOrigin) {
        this.cityOfOrigin = cityOfOrigin;
    }

    public double getOriginalWort() {
        return originalWort;
    }

    public void setOriginalWort(double originalWort) {
        this.originalWort = originalWort;
    }

    public int getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(int yearEstablished) {
        this.yearEstablished = yearEstablished;
    }

    public double getAlcoholicStrength() {
        return alcoholicStrength;
    }

    public void setAlcoholicStrength(double alcoholicStrength) {
        this.alcoholicStrength = alcoholicStrength;
    }

    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {

    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {

    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }

    public void assertClassInvariants() {

    }
}
