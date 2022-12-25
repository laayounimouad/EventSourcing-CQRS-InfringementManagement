package events.vehicule;

import events.BaseEvent;
import lombok.Getter;

public class VehiculeUpdatedEvent extends BaseEvent<String> {
    @Getter private String matriculeNumber;
    @Getter private String marque;
    @Getter private String puissanceFiscale;
    @Getter private String modele;
    @Getter private String ownerId;

    public VehiculeUpdatedEvent(String id, String matriculeNumber, String marque, String puissanceFiscale, String modele, String ownerId) {
        super(id);
        this.matriculeNumber = matriculeNumber;
        this.marque = marque;
        this.puissanceFiscale = puissanceFiscale;
        this.modele = modele;
        this.ownerId = ownerId;
    }
}
