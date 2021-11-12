package org.wahlzeit.model.beer;

import org.wahlzeit.main.ServiceMain;
import org.wahlzeit.model.*;
import org.wahlzeit.services.Persistent;
import org.wahlzeit.services.SysLog;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BeerPhotoManager extends PhotoManager {

    /**
     *
     */
    protected static final BeerPhotoManager instance = new BeerPhotoManager();

    /**
     * In-memory cache for photos
     */
    protected Map<PhotoId, BeerPhoto> photoCache = new HashMap<PhotoId, BeerPhoto>();

    /**
     *
     */
    protected PhotoTagCollector photoTagCollector = null;


    public BeerPhotoManager() {
        photoTagCollector = BeerPhotoFactory.getInstance().createPhotoTagCollector();
    }

    /**
     * @methodtype boolean-query
     * @methodproperties primitive
     */
    protected boolean doHasPhoto(PhotoId id) {
        return photoCache.containsKey(id);
    }

    /**
     *
     */
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
    protected BeerPhoto doGetPhotoFromId(PhotoId id) {
        return photoCache.get(id);
    }

    /**
     *
     */
    protected BeerPhoto createObject(ResultSet rset) throws SQLException {
        return BeerPhotoFactory.getInstance().createPhoto(rset);
    }

    /**
     * @methodtype command
     *
     * Load all persisted photos. Executed when Wahlzeit is restarted.
     */
    public void addPhoto(BeerPhoto photo) {
        PhotoId id = photo.getId();
        assertIsNewPhoto(id);
        doAddPhoto(photo);

        try {
            PreparedStatement stmt = getReadingStatement("INSERT INTO photos(id) VALUES(?)");
            createObject(photo, stmt, id.asInt());
            ServiceMain.getInstance().saveGlobals();
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }
    }

    /**
     * @methodtype command
     * @methodproperties primitive
     */
    protected void doAddPhoto(BeerPhoto myPhoto) {
        photoCache.put(myPhoto.getId(), myPhoto);
    }

    /**
     * @methodtype command
     * @param result
     */

    public void loadBeerPhotos(Collection<BeerPhoto> result) {
        try {
            PreparedStatement stmt = getReadingStatement("SELECT * FROM photos");
            readObjects(result, stmt);
            for (Iterator<BeerPhoto> i = result.iterator(); i.hasNext(); ) {
                Photo photo = i.next();
                if (!doHasPhoto(photo.getId())) {
                    doAddPhoto(photo);
                } else {
                    SysLog.logSysInfo("beerPhoto", photo.getId().asString(), "photo had already been loaded");
                }
            }
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }

        SysLog.logSysInfo("loaded all beerPhotos");
    }

    /**
     *
     */
    public void savePhoto(BeerPhoto photo) {
        try {
            PreparedStatement stmt = getUpdatingStatement("SELECT * FROM photos WHERE id = ?");
            updateObject(photo, stmt);
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }
    }

    /**
     *
     */
    public void savePhotos() {
        try {
            PreparedStatement stmt = getUpdatingStatement("SELECT * FROM photos WHERE id = ?");
            updateObjects(photoCache.values(), stmt);
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }
    }

    /**
     * @methodtype command
     *
     * Persists all available sizes of the Photo. If one size exceeds the limit of the persistence layer, e.g. > 1MB for
     * the Datastore, it is simply not persisted.
     */
    public Set<Photo> findPhotosByOwner(String ownerName) {
        Set<Photo> result = new HashSet<Photo>();
        try {
            PreparedStatement stmt = getReadingStatement("SELECT * FROM photos WHERE owner_name = ?");
            readObjects(result, stmt, ownerName);
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }

        for (Iterator<Photo> i = result.iterator(); i.hasNext(); ) {
            doAddPhoto(i.next());
        }

        return result;
    }

    /**
     *
     */
    public BeerPhoto getVisiblePhoto(PhotoFilter filter) {
        BeerPhoto result = getPhotoFromFilter(filter);

        if(result == null) {
            java.util.List<PhotoId> list = getFilteredPhotoIds(filter);
            filter.setDisplayablePhotoIds(list);
            result = getPhotoFromFilter(filter);
        }

        return result;
    }

    /**
     *
     */
    protected BeerPhoto getPhotoFromFilter(PhotoFilter filter) {
        PhotoId id = filter.getRandomDisplayablePhotoId();
        BeerPhoto result = getPhotoFromId(id);
        while((result != null) && !result.isVisible()) {
            id = filter.getRandomDisplayablePhotoId();
            result = getPhotoFromId(id);
            if ((result != null) && !result.isVisible()) {
                filter.addProcessedPhoto(result);
            }
        }

        return result;
    }

    /**
     *
     */
    protected java.util.List<PhotoId> getFilteredPhotoIds(PhotoFilter filter) {
        java.util.List<PhotoId> result = new LinkedList<PhotoId>();

        try {
            java.util.List<String> filterConditions = filter.getFilterConditions();

            int noFilterConditions = filterConditions.size();
            PreparedStatement stmt = getUpdatingStatementFromConditions(noFilterConditions);
            for (int i = 0; i < noFilterConditions; i++) {
                stmt.setString(i + 1, filterConditions.get(i));
            }

            SysLog.logQuery(stmt);
            ResultSet rset = stmt.executeQuery();

            if (noFilterConditions == 0) {
                noFilterConditions++;
            }

            int[] ids = new int[PhotoId.getCurrentIdAsInt() + 1];
            while(rset.next()) {
                int id = rset.getInt("photo_id");
                if (++ids[id] == noFilterConditions) {
                    PhotoId photoId = PhotoId.getIdFromInt(id);
                    if (!filter.isProcessedPhotoId(photoId)) {
                        result.add(photoId);
                    }
                }
            }
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }

        return result;
    }

    /**
     *
     */
    protected PreparedStatement getUpdatingStatementFromConditions(int no) throws SQLException {
        String query = "SELECT * FROM tags";
        if (no > 0) {
            query += " WHERE";
        }

        for (int i = 0; i < no; i++) {
            if (i > 0) {
                query += " OR";
            }
            query += " (tag = ?)";
        }

        return getUpdatingStatement(query);
    }

    /**
     *
     */
    protected void updateDependents(Persistent obj) throws SQLException {
        BeerPhoto photo = (BeerPhoto) obj;

        PreparedStatement stmt = getReadingStatement("DELETE FROM tags WHERE photo_id = ?");
        deleteObject(obj, stmt);

        stmt = getReadingStatement("INSERT INTO tags VALUES(?, ?)");
        Set<String> tags = new HashSet<String>();
        photoTagCollector.collect(tags, photo);
        for (Iterator<String> i = tags.iterator(); i.hasNext(); ) {
            String tag = i.next();
            stmt.setString(1, tag);
            stmt.setInt(2, photo.getId().asInt());
            SysLog.logQuery(stmt);
            stmt.executeUpdate();
        }
    }

    /**
     *
     */
    public BeerPhoto createPhoto(File file) throws Exception {
        PhotoId id = PhotoId.getNextId();
        BeerPhoto result = (BeerPhoto) PhotoUtil.createPhoto(file, id);
        addPhoto(result);
        return result;
    }

    /**
     * @methodtype assertion
     */
    protected void assertIsNewPhoto(PhotoId id) {
        if (hasPhoto(id)) {
            throw new IllegalStateException("Photo already exists!");
        }
    }

}
