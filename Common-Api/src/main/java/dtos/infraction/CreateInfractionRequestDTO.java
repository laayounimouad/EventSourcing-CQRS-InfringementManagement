package dtos.infraction;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInfractionRequestDTO {
    private Date date;
    private double vitesse;
    private double montant;
    private String radarId;
    private String vehicule_matricule;
}
