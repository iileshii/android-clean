package mobi.jedi.architecturehello.repository.provider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mobi.jedi.architecturehello.core.GithubApp;
import mobi.jedi.architecturehello.repository.entity.Repo;
import mobi.jedi.architecturehello.repository.provider.interfaces.IRepoProvider;
import mobi.jedi.architecturehello.repository.service.GithubApi;
import mobi.jedi.architecturehello.repository.service.response.RepoResponse;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class RepoProvider implements IRepoProvider {

    @Inject
    GithubApi mGithubApi;

    public RepoProvider() {
        GithubApp.getComponent().inject(this);
    }

    @Override
    public Observable<List<Repo>> getGoogleSamples() {
        return mGithubApi.getGoogleSamples()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Func1<List<RepoResponse>, List<Repo>>() {
                    @Override
                    public List<Repo> call(List<RepoResponse> repoResponses) {
                        return transformRepoResponses(repoResponses);
                    }
                });
    }

    private List<Repo> transformRepoResponses(List<RepoResponse> repoResponses) {
        List<Repo> repos = new ArrayList<>(repoResponses.size());
        for (RepoResponse repoResponse : repoResponses) {
            repos.add(transformRepoResponse(repoResponse));
        }
        return repos;
    }

    private Repo transformRepoResponse(RepoResponse repoResponse) {
        return new Repo(repoResponse.getId(), repoResponse.getName(), repoResponse.getUpdated());
    }
}
