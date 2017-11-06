package mobi.jedi.architecturehello.repository.holder.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import mobi.jedi.architecturehello.repository.entity.Commit;

/**
 * Created by alkuznetsov
 * on 09/08/2017.
 */

public interface ICommitHolder {

    @Nullable
    List<Commit> getCommits(@NonNull String repo);

    void updateCommits(@NonNull String repo, @NonNull List<Commit> commits);
}
