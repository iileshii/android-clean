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
import mobi.jedi.architecturehello.databinding.ItemCommitBinding;
import mobi.jedi.architecturehello.presenter.viewmodel.CommitViewModel;

/**
 * Created by alkuznetsov
 * on 08/08/2017.
 */

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.CommitViewHolder> {

    private List<CommitViewModel> mCommitViewModels;
    private Listener mListener;

    public CommitAdapter(Listener listener) {
        mListener = listener;
    }

    @Override
    public CommitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCommitBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.item_commit, parent, false);
        return new CommitViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(CommitViewHolder holder, int position) {
        CommitViewModel commitViewModel = mCommitViewModels.get(position);
        holder.updateCommit(commitViewModel);
    }

    @Override
    public int getItemCount() {
        return mCommitViewModels == null ? 0 : mCommitViewModels.size();
    }

    public void setCommitViewModels(List<CommitViewModel> commitViewModels) {
        mCommitViewModels = commitViewModels;
        notifyDataSetChanged();
    }

    public interface Listener {
        void onCommitClick(String author);
    }

    static final class CommitViewHolder extends RecyclerView.ViewHolder {

        ItemCommitBinding mBinding;
        private String mAuthor;
        private Listener mListener;

        CommitViewHolder(ItemCommitBinding binding, Listener listener) {
            super(binding.getRoot());

            mBinding = binding;
            mListener = listener;

            mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null && mAuthor != null) {
                        mListener.onCommitClick(mAuthor);
                    }
                }
            });
        }


        void updateCommit(CommitViewModel commitViewModel) {
            mAuthor = commitViewModel.getAuthorName();
            mBinding.sha.setText(commitViewModel.getSha());
            mBinding.name.setText(commitViewModel.getAuthorName());
            mBinding.date.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                    .format(commitViewModel.getDate()));
        }
    }
}
