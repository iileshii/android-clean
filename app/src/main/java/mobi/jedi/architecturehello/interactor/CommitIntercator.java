package mobi.jedi.architecturehello.interactor;

import java.util.List;

import javax.inject.Inject;

import mobi.jedi.architecturehello.core.GithubApp;
import mobi.jedi.architecturehello.interactor.interfaces.ICommitInteractor;
import mobi.jedi.architecturehello.repository.entity.Commit;
import mobi.jedi.architecturehello.repository.provider.interfaces.ICommitProvider;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class CommitIntercator implements ICommitInteractor {

    @Inject
    ICommitProvider mCommitProvider;

    public CommitIntercator() {
        GithubApp.getComponent().inject(this);
    }

    @Override
    public Observable<List<Commit>> loadCommits(String repo) {
        return mCommitProvider.getCommits(repo)
                .filter(new Func1<List<Commit>, Boolean>() {
                    @Override
                    public Boolean call(List<Commit> commits) {
                        return true;
                    }
                });
    }
}
