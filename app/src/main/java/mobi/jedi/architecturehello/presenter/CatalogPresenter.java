package mobi.jedi.architecturehello.presenter;


import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mobi.jedi.architecturehello.core.GithubApp;
import mobi.jedi.architecturehello.interactor.interfaces.IRepoInteractor;
import mobi.jedi.architecturehello.presenter.viewmodel.RepoViewModel;
import mobi.jedi.architecturehello.repository.entity.Repo;
import mobi.jedi.architecturehello.router.IRouter;
import mobi.jedi.architecturehello.view.interfaces.ICatalogView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class CatalogPresenter extends BasePresenter<ICatalogView> {

    @Inject
    IRepoInteractor mRepoInteractor;

    @Inject
    IRouter mRouter;

    private Subscription mRepoSubscription;

    public CatalogPresenter() {
        GithubApp.getComponent().inject(this);
    }

    @Override
    protected void onViewAttached() {
        mRepoSubscription = mRepoInteractor.loadRepos()
                .map(new Func1<List<Repo>, List<RepoViewModel>>() {
                    @Override
                    public List<RepoViewModel> call(List<Repo> repos) {
                        return transformRepos(repos);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RepoViewModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Throwable: ", e);
                    }

                    @Override
                    public void onNext(List<RepoViewModel> repoViewModels) {
                        if (getView() != null) {
                            getView().showRepos(repoViewModels);
                        }
                    }
                });
    }

    @Override
    protected void onViewDetached() {
        if (mRepoSubscription != null && !mRepoSubscription.isUnsubscribed()) {
            mRepoSubscription.unsubscribe();
        }
    }


    private List<RepoViewModel> transformRepos(List<Repo> repos) {
        List<RepoViewModel> viewModels = new ArrayList<>(repos.size());
        for (Repo repo : repos) {
            viewModels.add(transformRepo(repo));
        }
        return viewModels;
    }

    @NonNull
    private RepoViewModel transformRepo(Repo repo) {
        return new RepoViewModel(repo.getName(), repo.getUpdated());
    }

    public void onRepoClick(String repo) {
        if (getView() != null) {
            mRouter.startCommitsView(getView().getContext(), repo);
        }
    }
}
