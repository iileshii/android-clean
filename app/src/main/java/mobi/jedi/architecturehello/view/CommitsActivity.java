package mobi.jedi.architecturehello.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.List;

import mobi.jedi.architecturehello.R;
import mobi.jedi.architecturehello.databinding.ActivityCommitsBinding;
import mobi.jedi.architecturehello.presenter.CommitsPresenter;
import mobi.jedi.architecturehello.presenter.viewmodel.CommitViewModel;
import mobi.jedi.architecturehello.view.adapter.CommitAdapter;
import mobi.jedi.architecturehello.view.interfaces.ICommitsView;

public class CommitsActivity extends AppCompatActivity implements ICommitsView,
        CommitAdapter.Listener {

    private static final String EXTRA_REPO = "repoName";
    private static final long NO_ID = -1;

    private ActivityCommitsBinding mBinding;
    private CommitsPresenter mPresenter;
    private String mRepo;
    private CommitAdapter mAdapter;


    public static void startActivity(@NonNull Context context, @NonNull String repo) {
        if (TextUtils.isEmpty(repo)) {
            throw new IllegalArgumentException(
                    "Repo name is strictly required and must not be empty");
        }

        final Intent intent = new Intent(context, CommitsActivity.class);
        intent.putExtra(EXTRA_REPO, repo);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_commits);

        mRepo = getIntent().getStringExtra(EXTRA_REPO);

        if (TextUtils.isEmpty(mRepo)) {
            throw new IllegalArgumentException(
                    "Repo name is strictly required and must not be empty");
        }

        if (mPresenter == null) {
            mPresenter = new CommitsPresenter(mRepo);
        }

        mAdapter = new CommitAdapter(this);
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
    public Context getContext() {
        return this;
    }

    @Override
    public void showCommits(List<CommitViewModel> commits) {
        mAdapter.setCommitViewModels(commits);
    }

    @Override
    public void onCommitClick(String author) {
        Toast.makeText(this, "commit by " + author, Toast.LENGTH_SHORT).show();
    }
}
