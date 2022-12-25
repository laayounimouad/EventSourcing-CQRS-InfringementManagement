package ma.laayouni.ImmatriculationService.commandSide.aggregates;

import commands.owner.CreateOwnerCommand;
import commands.owner.UpdateOwnerCommand;
import commands.vehicule.CreateVehiculeCommand;
import commands.vehicule.UpdateVehiculeCommand;
import events.owner.OwnerCreatedEvent;
import events.owner.OwnerUpdatedEvent;
import events.vehicule.VehiculeCreatedEvent;
import events.vehicule.VehiculeUpdatedEvent;
import ma.laayouni.ImmatriculationService.querySide.entities.Owner;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.ManyToOne;
import java.util.Date;

@Aggregate
public class VehiculeAggregate {
    @AggregateIdentifier
    private String id;
    private String matriculeNumber;
    private String marque;
    private String puissanceFiscale;
    private String modele;
    private String ownerId;

    public VehiculeAggregate() {
    }



    @CommandHandler
    public VehiculeAggregate(CreateVehiculeCommand command){
        AggregateLifecycle.apply(new VehiculeCreatedEvent(
                command.getId(),
                command.getMatriculeNumber(),
                command.getMarque(),
                command.getPuissanceFiscale(),
                command.getModele(),
                command.getOwnerId()
        ));
    }

    @EventSourcingHandler
    public void on(VehiculeCreatedEvent event){
        this.id=event.getId();
        this.marque= event.getMarque();
        this.matriculeNumber= event.getMatriculeNumber();
        this.modele=event.getModele();
        this.puissanceFiscale=event.getPuissanceFiscale();
        this.ownerId= event.getOwnerId();
    }

    @CommandHandler
    public void handle(UpdateVehiculeCommand command){
        AggregateLifecycle.apply(new VehiculeUpdatedEvent(
                command.getId(),
                command.getMatriculeNumber(),
                command.getMarque(),
                command.getPuissanceFiscale(),
                command.getModele(),
                command.getOwnerId()
        ));
    }
    @EventSourcingHandler
    public void on(VehiculeUpdatedEvent event){
        this.marque= event.getMarque();
        this.matriculeNumber= event.getMatriculeNumber();
        this.modele=event.getModele();
        this.puissanceFiscale=event.getPuissanceFiscale();
        this.ownerId= event.getOwnerId();
    }

}
