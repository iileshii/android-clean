package mobi.jedi.architecturehello.repository.provider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mobi.jedi.architecturehello.core.GithubApp;
import mobi.jedi.architecturehello.repository.entity.Commit;
import mobi.jedi.architecturehello.repository.holder.interfaces.ICommitHolder;
import mobi.jedi.architecturehello.repository.provider.interfaces.ICommitProvider;
import mobi.jedi.architecturehello.repository.service.GithubApi;
import mobi.jedi.architecturehello.repository.service.response.CommitResponse;
import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class CommitProvider implements ICommitProvider {

    @Inject
    GithubApi mGithubApi;

    @Inject
    ICommitHolder mCommitHolder;

    public CommitProvider() {
        GithubApp.getComponent().inject(this);
    }

    @Override
    public Observable<List<Commit>> getCommits(final String repo) {
        return Observable.create(new Action1<Emitter<List<Commit>>>() {
            @Override
            public void call(Emitter<List<Commit>> listEmitter) {
                final List<Commit> commits = mCommitHolder.getCommits(repo);
                if (commits != null && !commits.isEmpty()) {
                    listEmitter.onNext(commits);
                }
                listEmitter.onCompleted();
            }
        }, Emitter.BackpressureMode.LATEST).switchIfEmpty(mGithubApi.getCommits(repo)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Func1<List<CommitResponse>, List<Commit>>() {
                    @Override
                    public List<Commit> call(List<CommitResponse> commitResponses) {
                        return transformCommitResponses(commitResponses);
                    }
                })
                .doOnNext(new Action1<List<Commit>>() {
                    @Override
                    public void call(List<Commit> commits) {
                        mCommitHolder.updateCommits(repo, commits);
                    }
                }));
    }

    private List<Commit> transformCommitResponses(List<CommitResponse> commitResponses) {
        List<Commit> commits = new ArrayList<>(commitResponses.size());
        for (CommitResponse commitResponse : commitResponses) {
            commits.add(transformCommitResponse(commitResponse));
        }
        return commits;
    }

    private Commit transformCommitResponse(CommitResponse commitResponse) {
        return new Commit(commitResponse.getSha(),
                commitResponse.getCommitData().getAuthor().getName(),
                commitResponse.getCommitData().getAuthor().getDate());
    }
}
