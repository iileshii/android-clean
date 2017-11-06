package mobi.jedi.architecturehello.core.injection;

import javax.inject.Singleton;

import dagger.Component;
import mobi.jedi.architecturehello.interactor.CommitIntercator;
import mobi.jedi.architecturehello.interactor.RepoIntercator;
import mobi.jedi.architecturehello.presenter.CatalogPresenter;
import mobi.jedi.architecturehello.presenter.CommitsPresenter;
import mobi.jedi.architecturehello.presenter.MainPresenter;
import mobi.jedi.architecturehello.repository.provider.CommitProvider;
import mobi.jedi.architecturehello.repository.provider.RepoProvider;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

@Singleton
@Component(modules = {AppModule.class, CatalogModule.class, CommitModule.class,
        NetworkModule.class})
public interface AppComponent {

    void inject(CatalogPresenter catalogPresenter);

    void inject(RepoIntercator repoIntercator);

    void inject(RepoProvider repoProvider);

    void inject(CommitsPresenter commitsPresenter);

    void inject(CommitProvider commitProvider);

    void inject(CommitIntercator commitIntercator);

    void inject(MainPresenter mainPresenter);
}