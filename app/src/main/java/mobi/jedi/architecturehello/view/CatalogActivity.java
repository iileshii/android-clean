package mobi.jedi.architecturehello.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import mobi.jedi.architecturehello.R;
import mobi.jedi.architecturehello.databinding.ActivityCatalogBinding;
import mobi.jedi.architecturehello.presenter.CatalogPresenter;
import mobi.jedi.architecturehello.presenter.viewmodel.RepoViewModel;
import mobi.jedi.architecturehello.view.adapter.RepoAdapter;
import mobi.jedi.architecturehello.view.interfaces.ICatalogView;

public class CatalogActivity extends AppCompatActivity implements ICatalogView,
        RepoAdapter.Listener {

    private ActivityCatalogBinding mBinding;
    private CatalogPresenter mPresenter;
    private RepoAdapter mAdapter;

    public static void startActivity(@NonNull Context context) {
        final Intent intent = new Intent(context, CatalogActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_catalog);

        if (mPresenter == null) {
            mPresenter = new CatalogPresenter();
        }

        mAdapter = new RepoAdapter(this);
        final LinearLayoutManager layout =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        final DividerItemDecoration decor =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);

        mBinding.recyclerView.setLayoutManager(layout);
        mBinding.recyclerView.addItemDecoration(decor);
        mBinding.recyclerView.setAdapter(mAdapter);
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
    public void showRepos(List<RepoViewModel> repoViewModels) {
        mAdapter.setRepoViewModels(repoViewModels);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onRepoClick(String repo) {
        mPresenter.onRepoClick(repo);
    }
}
