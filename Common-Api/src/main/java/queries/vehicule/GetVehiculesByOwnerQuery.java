package queries.vehicule;

import lombok.Getter;

public class GetVehiculesByOwnerQuery {
    @Getter private String ownerId;

    public GetVehiculesByOwnerQuery(String ownerId) {
        this.ownerId = ownerId;
    }
}
