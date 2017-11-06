package mobi.jedi.architecturehello.router;

import android.content.Context;

import mobi.jedi.architecturehello.view.CatalogActivity;
import mobi.jedi.architecturehello.view.CommitsActivity;

/**
 * Created by alkuznetsov
 * on 08/08/2017.
 */

public class Router implements IRouter {

    @Override
    public void startCatalogView(Context context) {
        CatalogActivity.startActivity(context);
    }

    @Override
    public void startCommitsView(Context context, String repo) {
        CommitsActivity.startActivity(context, repo);
    }
}
