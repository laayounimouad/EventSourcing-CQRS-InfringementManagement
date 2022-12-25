package events.Infraction;

import events.BaseEvent;
import lombok.Getter;

import java.util.Date;

public class InfractionCreatedEvent extends BaseEvent<String> {
    @Getter  Date date;
    @Getter  private double vitesse;

    @Getter  private double montant;
    @Getter  private String radarId;
    @Getter  private String vehicule_matricule;

    public InfractionCreatedEvent(String id, Date date, double vitesse,  double montant, String radarId, String vehicule_matricule) {
        super(id);
        this.date = date;
        this.vitesse = vitesse;
        this.montant = montant;
        this.radarId = radarId;
        this.vehicule_matricule = vehicule_matricule;
    }
}
