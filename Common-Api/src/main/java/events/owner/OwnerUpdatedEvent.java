package events.owner;

import events.BaseEvent;
import lombok.Getter;

import java.util.Date;

public class OwnerUpdatedEvent extends BaseEvent<String> {
    @Getter private String nom;
    @Getter private Date date_naissance;
    @Getter private String email;

    public OwnerUpdatedEvent(String id, String nom, Date date_naissance, String email) {
        super(id);
        this.nom = nom;
        this.date_naissance = date_naissance;
        this.email = email;
    }
}
