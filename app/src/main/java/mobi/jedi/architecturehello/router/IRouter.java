package mobi.jedi.architecturehello.router;

import android.content.Context;

/**
 * Created by alkuznetsov
 * on 08/08/2017.
 */

public interface IRouter {
    void startCatalogView(Context context);

    void startCommitsView(Context context, String repo);
}
