package org.wahlzeit.model.beer;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BeerType extends DataObject {

    protected BeerType superType = null;
    protected Set<BeerType> subTypes = new HashSet<BeerType>();
    private String beerTypeName;


    public BeerType(String beerTypeName) {
        this.beerTypeName = beerTypeName;
    }

    public BeerType getSuperType(){
        return superType;
    }

    public void setSuperType(BeerType superType) {
        this.superType = superType;
    }

    public Iterator<BeerType> getSubTypeIterator(){
        return subTypes.iterator();
    }

    public void addSubType(BeerType type){
        assert (type != null) : "tried to set null sub-type";
        type.setSuperType(this);
        subTypes.add(type);
    }

    public Beer createInstance(){
        return new Beer(this);
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
}
