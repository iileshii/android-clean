package mobi.jedi.architecturehello.repository.provider.interfaces;

import java.util.List;

import mobi.jedi.architecturehello.repository.entity.Commit;
import rx.Observable;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public interface ICommitProvider {

    Observable<List<Commit>> getCommits(String repo);
}
