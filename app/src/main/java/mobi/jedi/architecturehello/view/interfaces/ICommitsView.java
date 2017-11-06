package mobi.jedi.architecturehello.view.interfaces;

import java.util.List;

import mobi.jedi.architecturehello.presenter.viewmodel.CommitViewModel;

/**
 * Created by alkuznetsov
 * on 08/08/2017.
 */

public interface ICommitsView extends IView {

    void showCommits(List<CommitViewModel> commits);
}
