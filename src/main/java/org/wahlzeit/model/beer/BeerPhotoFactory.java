package org.wahlzeit.model.beer;

import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.SysLog;
import org.wahlzeit.utils.PatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;


@PatternInstance(
        patternName = "Abstract Factory",
        participants = {
                "ConcreteFactory"
        }
)
@PatternInstance(
        patternName = "Singleton",
        participants = {
                "Singleton"
        }
)
//basically just a copy of PhotoFactory with adjusted variable types
public class BeerPhotoFactory extends PhotoFactory {

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static BeerPhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized BeerPhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting generic BeerPhotoFactory");
            setInstance(new BeerPhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of BeerPhotoFactory.
     */
    protected static synchronized void setInstance(BeerPhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize BeerPhotoFactory twice");
        }

        instance = photoFactory;
    }

    /**
     *
     */
    protected BeerPhotoFactory() {
        // do nothing
    }

    /**
     * @methodtype factory
     */
    public BeerPhoto createPhoto() {
        //Constructor call
        return new BeerPhoto();
    }

    /**
     *
     */
    public BeerPhoto createPhoto(PhotoId id) {
        return new BeerPhoto(id);
    }

    /**
     *
     */
    public BeerPhoto createPhoto(ResultSet rs) throws SQLException {
        return new BeerPhoto(rs);
    }
}

