package mobi.jedi.architecturehello.core.injection;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mobi.jedi.architecturehello.interactor.RepoIntercator;
import mobi.jedi.architecturehello.interactor.interfaces.IRepoInteractor;
import mobi.jedi.architecturehello.repository.provider.RepoProvider;
import mobi.jedi.architecturehello.repository.provider.interfaces.IRepoProvider;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

@Module
public class CatalogModule {

    @Provides
    @Singleton
    @NonNull
    IRepoInteractor provideRepoInteractor() {
        return new RepoIntercator();
    }

    @Provides
    @Singleton
    @NonNull
    IRepoProvider provideRepoProvider() {
        return new RepoProvider();
    }
}
