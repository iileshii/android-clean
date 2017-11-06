package mobi.jedi.architecturehello.repository.holder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobi.jedi.architecturehello.repository.entity.Commit;
import mobi.jedi.architecturehello.repository.holder.interfaces.ICommitHolder;

/**
 * Created by alkuznetsov
 * on 09/08/2017.
 */

public class CommitHolder implements ICommitHolder {

    private final Map<String, List<Commit>> mCommits;

    public CommitHolder() {
        mCommits = new HashMap<>();
    }

    @Override
    @Nullable
    public List<Commit> getCommits(@NonNull String repo) {
        return mCommits.get(repo);
    }

    @Override
    public void updateCommits(@NonNull String repo, @NonNull List<Commit> commits) {
        mCommits.put(repo, commits);
    }
}
