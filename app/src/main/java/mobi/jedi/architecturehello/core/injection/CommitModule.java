package mobi.jedi.architecturehello.core.injection;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mobi.jedi.architecturehello.interactor.CommitIntercator;
import mobi.jedi.architecturehello.interactor.interfaces.ICommitInteractor;
import mobi.jedi.architecturehello.repository.holder.CommitHolder;
import mobi.jedi.architecturehello.repository.holder.interfaces.ICommitHolder;
import mobi.jedi.architecturehello.repository.provider.CommitProvider;
import mobi.jedi.architecturehello.repository.provider.interfaces.ICommitProvider;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

@Module
public class CommitModule {

    @Provides
    @Singleton
    @NonNull
    ICommitInteractor provideCommitInteractor() {
        return new CommitIntercator();
    }

    @Provides
    @Singleton
    @NonNull
    ICommitProvider provideCommitProvider() {
        return new CommitProvider();
    }

    @Provides
    @Singleton
    @NonNull
    ICommitHolder provideCommitHolder() {
        return new CommitHolder();
    }
}
