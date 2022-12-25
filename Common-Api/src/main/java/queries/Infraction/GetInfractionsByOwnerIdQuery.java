package queries.Infraction;

import lombok.Getter;

public class GetInfractionsByOwnerIdQuery {
    @Getter private String ownerId;

    public GetInfractionsByOwnerIdQuery(String ownerId) {
        this.ownerId = ownerId;
    }
}
