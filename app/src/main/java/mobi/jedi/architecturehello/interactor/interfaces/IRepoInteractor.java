package mobi.jedi.architecturehello.interactor.interfaces;

import java.util.List;

import mobi.jedi.architecturehello.repository.entity.Repo;
import rx.Observable;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public interface IRepoInteractor {

    Observable<List<Repo>> loadRepos();
}
