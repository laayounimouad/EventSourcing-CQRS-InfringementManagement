package queries.Infraction;

import lombok.Getter;

public class GetInfractionsByMatriculeQuery {
    public GetInfractionsByMatriculeQuery(String matricule) {
        this.matricule = matricule;
    }

    @Getter private String matricule;
}
