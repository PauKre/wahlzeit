package org.wahlzeit.model.beer;

import org.wahlzeit.model.*;
import org.wahlzeit.services.SysLog;
import org.wahlzeit.utils.PatternInstance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@PatternInstance(patternName = "Mediator", participants = {
        "Mediator"
})
public class BeerPhotoManager extends PhotoManager {

    /**
     *
     */
    protected static final BeerPhotoManager instance = new BeerPhotoManager();

    /**
     * In-memory cache for photos
     */
    protected Map<PhotoId, BeerPhoto> beerPhotoCache = new HashMap<PhotoId, BeerPhoto>();


    public BeerPhotoManager() {
        photoTagCollector = BeerPhotoFactory.getInstance().createPhotoTagCollector();
    }

    public static BeerPhoto getBeerPhoto(String id) {
        return getBeerPhoto(PhotoId.getIdFromString(id));
    }

    public static BeerPhoto getBeerPhoto(PhotoId id) {
        return instance.getPhotoFromId(id);
    }

    public BeerPhoto getPhotoFromId(PhotoId id) {
        if (id.isNullId()) {
            return null;
        }

        BeerPhoto result = doGetPhotoFromId(id);

        if (result == null) {
            try {
                PreparedStatement stmt = getReadingStatement("SELECT * FROM photos WHERE id = ?");
                result = (BeerPhoto) readObject(stmt, id.asInt());
            } catch (SQLException sex) {
                SysLog.logThrowable(sex);
            }
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }

    /**
     * @methodtype get
     * @methodproperties primitive
     */
    @Override
    protected BeerPhoto doGetPhotoFromId(PhotoId id) {
        return beerPhotoCache.get(id);
    }

    /**
     *
     */
    @Override
    protected BeerPhoto createObject(ResultSet rset) throws SQLException {
        return BeerPhotoFactory.getInstance().createPhoto(rset);
    }

    //addPhoto is ommitted as the super method is sufficient here

    /**
     * @methodtype command
     * @methodproperties primitive
     */
    protected void doAddPhoto(Photo myPhoto) {
        beerPhotoCache.put(myPhoto.getId(), (BeerPhoto) myPhoto);
    }

    //savePhoto is ommitted as the super method is sufficient here

    //getVisiblePhoto is ommitted as the super method is sufficient here

    //getPhotoFromFilter is ommitted as the super method is sufficient here

}
