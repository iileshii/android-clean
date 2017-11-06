package mobi.jedi.architecturehello.interactor;

import java.util.List;

import javax.inject.Inject;

import mobi.jedi.architecturehello.core.GithubApp;
import mobi.jedi.architecturehello.interactor.interfaces.IRepoInteractor;
import mobi.jedi.architecturehello.repository.entity.Repo;
import mobi.jedi.architecturehello.repository.provider.interfaces.IRepoProvider;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class RepoIntercator implements IRepoInteractor {

    @Inject
    IRepoProvider mRepoProvider;

    public RepoIntercator() {
        GithubApp.getComponent().inject(this);
    }

    @Override
    public Observable<List<Repo>> loadRepos() {
        return mRepoProvider.getGoogleSamples().filter(new Func1<List<Repo>, Boolean>() {
            @Override
            public Boolean call(List<Repo> repos) {
                return true;
            }
        });
    }
}
