package mobi.jedi.architecturehello.presenter.viewmodel;

import java.util.Date;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class RepoViewModel {
    private final String mName;
    private final Date mUpdated;

    public RepoViewModel(String name, Date updated) {
        mName = name;
        mUpdated = updated;
    }

    public String getName() {
        return mName;
    }

    public Date getUpdated() {
        return mUpdated;
    }
}
