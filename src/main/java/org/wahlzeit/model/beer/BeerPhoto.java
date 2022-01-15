package org.wahlzeit.model.beer;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoException;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.utils.PatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

@PatternInstance(
        patternName = "AbstractFactory",
        participants = {
                "ConcreteProduct"
        }
)
@PatternInstance(
        patternName = "Mediator",
        participants = {
                "Managed Object"
        }
)
public class BeerPhoto extends Photo {


    //5 new attributes are being added to the BeerPhoto
    private Beer beer;


    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    //    The readFrom method calls super, then reads the BeerPhoto attributes from the resultSet
    @Override
    public void readFrom(ResultSet rset) throws SQLException {

    }

    //    The writeOn method calls super, then updates the BeerPhoto attributes in the resultSet
    @Override
    public void writeOn(ResultSet rset) throws SQLException {

    }

    //the method assures that the beerStyle is not null
    private void assertClassInvariants() throws PhotoException {
        beer.assertClassInvariants();
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

}
