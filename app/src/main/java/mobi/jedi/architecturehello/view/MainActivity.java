package mobi.jedi.architecturehello.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mobi.jedi.architecturehello.R;
import mobi.jedi.architecturehello.databinding.ActivityMainBinding;
import mobi.jedi.architecturehello.presenter.MainPresenter;
import mobi.jedi.architecturehello.view.interfaces.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView {

    private MainPresenter mPresenter;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (mPresenter == null) {
            mPresenter = new MainPresenter();
        }

        mBinding.setHandler(mPresenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
