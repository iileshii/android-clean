package mobi.jedi.architecturehello.repository.service;

import java.util.List;

import mobi.jedi.architecturehello.repository.service.response.CommitResponse;
import mobi.jedi.architecturehello.repository.service.response.RepoResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public interface GithubApi {

    @GET("/users/googlesamples/repos")
    Observable<List<RepoResponse>> getGoogleSamples();

    @GET("/repos/googlesamples/{repo}/commits")
    Observable<List<CommitResponse>> getCommits(@Path("repo") String repo);
}
