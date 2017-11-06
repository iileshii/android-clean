package mobi.jedi.architecturehello.repository.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public final class CommitResponse {
    @SerializedName("sha")
    private final String mSha;
    @SerializedName("commit")
    private final CommitData mCommitData;

    public CommitResponse(String sha, CommitData commitData) {
        mSha = sha;
        mCommitData = commitData;
    }

    public String getSha() {
        return mSha;
    }

    public CommitData getCommitData() {
        return mCommitData;
    }

    public final static class CommitData {
        @SerializedName("author")
        private final Author mAuthor;

        private CommitData(Author author) {
            mAuthor = author;
        }

        public Author getAuthor() {
            return mAuthor;
        }

        public final static class Author {
            @SerializedName("name")
            private final String mName;
            @SerializedName("date")
            private final Date mDate;

            private Author(String name, Date date) {
                mName = name;
                mDate = date;
            }

            public String getName() {
                return mName;
            }

            public Date getDate() {
                return mDate;
            }
        }
    }
}
