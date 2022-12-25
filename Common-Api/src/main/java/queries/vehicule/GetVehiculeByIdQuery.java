package queries.vehicule;

import lombok.Getter;

public class GetVehiculeByIdQuery {
    @Getter private String vehiculeId;

    public GetVehiculeByIdQuery(String vehiculeId) {
        this.vehiculeId = vehiculeId;
    }
}
