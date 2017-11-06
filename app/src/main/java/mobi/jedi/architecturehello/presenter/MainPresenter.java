package mobi.jedi.architecturehello.presenter;

import javax.inject.Inject;

import mobi.jedi.architecturehello.core.GithubApp;
import mobi.jedi.architecturehello.presenter.viewhandler.MainViewHandler;
import mobi.jedi.architecturehello.router.IRouter;
import mobi.jedi.architecturehello.view.interfaces.IMainView;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class MainPresenter extends BasePresenter<IMainView> implements MainViewHandler {

    @Inject
    IRouter mRouter;

    public MainPresenter() {
        GithubApp.getComponent().inject(this);
    }

    @Override
    protected void onViewAttached() {

    }

    @Override
    protected void onViewDetached() {

    }

    @Override
    public void onCatalogClick() {
        if (getView() != null) {
            mRouter.startCatalogView(getView().getContext());
        }
    }
}
