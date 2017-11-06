package mobi.jedi.architecturehello.repository.entity;

import java.util.Date;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class Repo {
    private final long mId;
    private final String mName;
    private final Date mUpdated;

    public Repo(long id, String name, Date updated) {
        mId = id;
        mName = name;
        mUpdated = updated;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Date getUpdated() {
        return mUpdated;
    }
}
