package mobi.jedi.architecturehello.presenter.viewmodel;

import java.util.Date;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class CommitViewModel {
    private final String mSha;
    private final String mAuthorName;
    private final Date mDate;

    public CommitViewModel(String sha, String authorName, Date date) {
        mSha = sha;
        mAuthorName = authorName;
        mDate = date;
    }

    public String getSha() {
        return mSha;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public Date getDate() {
        return mDate;
    }
}
