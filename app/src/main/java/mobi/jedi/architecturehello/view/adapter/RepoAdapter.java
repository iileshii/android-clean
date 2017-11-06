package mobi.jedi.architecturehello.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import mobi.jedi.architecturehello.R;
import mobi.jedi.architecturehello.databinding.ItemRepoBinding;
import mobi.jedi.architecturehello.presenter.viewmodel.RepoViewModel;

/**
 * Created by alkuznetsov
 * on 08/08/2017.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private List<RepoViewModel> mRepoViewModels;
    private Listener mListener;

    public RepoAdapter(Listener listener) {
        mListener = listener;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRepoBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.item_repo, parent, false);
        return new RepoViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        RepoViewModel repoViewModel = mRepoViewModels.get(position);
        holder.updateRepo(repoViewModel);
    }

    @Override
    public int getItemCount() {
        return mRepoViewModels == null ? 0 : mRepoViewModels.size();
    }

    public void setRepoViewModels(List<RepoViewModel> repoViewModels) {
        mRepoViewModels = repoViewModels;
        notifyDataSetChanged();
    }

    public interface Listener {
        void onRepoClick(String repo);
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        ItemRepoBinding mBinding;
        private String mRepo;
        private Listener mListener;

        RepoViewHolder(ItemRepoBinding binding, Listener listener) {
            super(binding.getRoot());

            mBinding = binding;
            mListener = listener;

            mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRepoClick(mRepo);
                    }
                }
            });
        }


        void updateRepo(RepoViewModel repoViewModel) {
            mRepo = repoViewModel.getName();
            mBinding.name.setText(repoViewModel.getName());
            mBinding.date.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                    .format(repoViewModel.getUpdated()));
        }
    }
}
