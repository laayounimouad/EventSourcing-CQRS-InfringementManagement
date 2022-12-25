package ma.laayouni.InfractionService.querySide.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Infraction {
    @Id
    private String id;
    private Date date;
    private double vitesse;
    private double montant;
    private String radarId;
    private String vehicule_matricule;

    @Transient
    private Radar radar;
    @Transient
    private Vehicule vehicule;
}
