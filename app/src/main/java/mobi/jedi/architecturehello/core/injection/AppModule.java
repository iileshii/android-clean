package mobi.jedi.architecturehello.core.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mobi.jedi.architecturehello.router.IRouter;
import mobi.jedi.architecturehello.router.Router;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(@NonNull Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    @NonNull
    IRouter provideRouter() {
        return new Router();
    }
}
