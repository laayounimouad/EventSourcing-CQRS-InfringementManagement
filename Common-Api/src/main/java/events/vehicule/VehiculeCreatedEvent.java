package events.vehicule;

import events.BaseEvent;
import lombok.Getter;

import java.util.Date;

public class VehiculeCreatedEvent extends BaseEvent<String> {
    @Getter private String matriculeNumber;
    @Getter private String marque;
    @Getter private String puissanceFiscale;
    @Getter private String modele;
    @Getter private String ownerId;

    public VehiculeCreatedEvent(String id, String matriculeNumber, String marque, String puissanceFiscale, String modele, String ownerId) {
        super(id);
        this.matriculeNumber = matriculeNumber;
        this.marque = marque;
        this.puissanceFiscale = puissanceFiscale;
        this.modele = modele;
        this.ownerId = ownerId;
    }
}
