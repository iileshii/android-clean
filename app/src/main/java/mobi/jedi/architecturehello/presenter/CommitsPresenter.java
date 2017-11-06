package mobi.jedi.architecturehello.presenter;


import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mobi.jedi.architecturehello.core.GithubApp;
import mobi.jedi.architecturehello.interactor.interfaces.ICommitInteractor;
import mobi.jedi.architecturehello.presenter.viewmodel.CommitViewModel;
import mobi.jedi.architecturehello.repository.entity.Commit;
import mobi.jedi.architecturehello.view.interfaces.ICommitsView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class CommitsPresenter extends BasePresenter<ICommitsView> {

    @Inject
    ICommitInteractor mCommitInteractor;
    private String mRepo;
    private Subscription mCommitSubscription;

    public CommitsPresenter(String repo) {
        mRepo = repo;
        GithubApp.getComponent().inject(this);
    }

    @Override
    protected void onViewAttached() {
        mCommitSubscription = mCommitInteractor.loadCommits(mRepo)
                .map(new Func1<List<Commit>, List<CommitViewModel>>() {
                    @Override
                    public List<CommitViewModel> call(List<Commit> commits) {
                        return transformCommits(commits);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CommitViewModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Throwable: ", e);
                    }

                    @Override
                    public void onNext(List<CommitViewModel> commitViewModels) {
                        if (getView() != null) {
                            getView().showCommits(commitViewModels);
                        }
                    }
                });
    }

    @Override
    protected void onViewDetached() {
        if (mCommitSubscription != null && !mCommitSubscription.isUnsubscribed()) {
            mCommitSubscription.unsubscribe();
        }

    }

    private List<CommitViewModel> transformCommits(List<Commit> commits) {
        List<CommitViewModel> viewModels = new ArrayList<>(commits.size());
        for (Commit commit : commits) {
            viewModels.add(transformCommit(commit));
        }
        return viewModels;
    }

    @NonNull
    private CommitViewModel transformCommit(Commit commit) {
        return new CommitViewModel(commit.getSha(), commit.getAuthorName(), commit.getDate());
    }
}
