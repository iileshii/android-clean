package mobi.jedi.architecturehello.core;

import android.app.Application;

import mobi.jedi.architecturehello.core.injection.AppComponent;
import mobi.jedi.architecturehello.core.injection.AppModule;
import mobi.jedi.architecturehello.core.injection.CatalogModule;
import mobi.jedi.architecturehello.core.injection.CommitModule;
import mobi.jedi.architecturehello.core.injection.DaggerAppComponent;
import mobi.jedi.architecturehello.core.injection.NetworkModule;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class GithubApp extends Application {

    private static AppComponent sComponent;

    public static AppComponent getComponent() {
        return sComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sComponent = buildComponent();
    }


    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .catalogModule(new CatalogModule())
                .commitModule(new CommitModule())
                .networkModule(new NetworkModule())
                .build();
    }

}
