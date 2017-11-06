package mobi.jedi.architecturehello.core.injection;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mobi.jedi.architecturehello.repository.service.GithubApi;
import mobi.jedi.architecturehello.repository.service.NetworkService;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    @NonNull
    GithubApi provideGithubApi() {
        return new NetworkService().getNetworkService();
    }
}
