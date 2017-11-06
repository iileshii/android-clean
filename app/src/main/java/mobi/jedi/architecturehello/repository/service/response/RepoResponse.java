package mobi.jedi.architecturehello.repository.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public final class RepoResponse {
    @SerializedName("id")
    private final long id;
    @SerializedName("name")
    private final String name;
    @SerializedName("updated_at")
    private final Date updated;

    public RepoResponse(long id, String name, Date updated) {
        this.id = id;
        this.name = name;
        this.updated = updated;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getUpdated() {
        return updated;
    }
}
