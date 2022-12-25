package ma.laayouni.ImmatriculationService.commandSide.aggregates;

import commands.owner.CreateOwnerCommand;
import commands.owner.UpdateOwnerCommand;
import commands.radar.CreateRadarCommand;
import commands.radar.UpdateRadarCommand;
import events.owner.OwnerCreatedEvent;
import events.owner.OwnerUpdatedEvent;
import events.radar.RadarCreatedEvent;
import events.radar.RadarUpdatedEvent;
import exceptions.EntryNegativeException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
public class OwnerAggregate {
    @AggregateIdentifier
    private String id;
    private String nom;
    private Date date_naissance;
    private String email;

    public OwnerAggregate() {
    }


    @CommandHandler
    public OwnerAggregate(CreateOwnerCommand command){
        AggregateLifecycle.apply(new OwnerCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getDate_naissance(),
                command.getEmail()
        ));
    }

    @EventSourcingHandler
    public void on(OwnerCreatedEvent event){
        this.id=event.getId();
        this.nom= event.getNom();
        this.date_naissance= event.getDate_naissance();
        this.email=event.getEmail();
    }

    @CommandHandler
    public void handle(UpdateOwnerCommand command){
        AggregateLifecycle.apply(new OwnerUpdatedEvent(
                command.getId(),
                command.getNom(),
                command.getDate_naissance(),
                command.getEmail()
        ));
    }
    @EventSourcingHandler
    public void on(OwnerUpdatedEvent event){
        this.nom= event.getNom();
        this.date_naissance= event.getDate_naissance();
        this.email=event.getEmail();
    }


}
