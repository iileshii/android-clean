package mobi.jedi.architecturehello.presenter;

import android.support.annotation.Nullable;

import mobi.jedi.architecturehello.view.interfaces.IView;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

abstract class BasePresenter<T extends IView> {

    @Nullable
    private T mView;

    final public void attachView(T view) {
        mView = view;
        onViewAttached();
    }

    final public void detachView() {
        mView = null;
        onViewDetached();
    }

    @Nullable
    final T getView() {
        return mView;
    }

    protected abstract void onViewAttached();

    protected abstract void onViewDetached();
}
