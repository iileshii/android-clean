package mobi.jedi.architecturehello.view.interfaces;

import java.util.List;

import mobi.jedi.architecturehello.presenter.viewmodel.RepoViewModel;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public interface ICatalogView extends IView {
    void showRepos(List<RepoViewModel> repoViewModels);
}
