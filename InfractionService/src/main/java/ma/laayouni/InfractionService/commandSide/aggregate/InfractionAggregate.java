package ma.laayouni.InfractionService.commandSide.aggregate;

import commands.infraction.CreateInfractionCommand;
import commands.infraction.UpdateInfractionCommand;
import events.Infraction.InfractionCreatedEvent;
import events.Infraction.InfractionUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
public class InfractionAggregate {
    @AggregateIdentifier
    private String id;
    private Date date;
    private double vitesse;
    private double montant;
    private String radarId;
    private String vehicule_matricule;

    public InfractionAggregate() {
    }

    @CommandHandler
    public InfractionAggregate(CreateInfractionCommand command) {
        AggregateLifecycle.apply(new InfractionCreatedEvent(
                command.getId(),
                command.getDate(),
                command.getVitesse(),
                command.getMontant(),
                command.getRadarId(),
                command.getVehicule_matricule()
        ));
    }

    @EventSourcingHandler
    public void on(InfractionCreatedEvent event) {
        this.id = event.getId();
        this.date = event.getDate();
        this.vitesse= event.getVitesse();
        this.montant=event.getMontant();
        this.radarId = event.getRadarId();
        this.vehicule_matricule= event.getVehicule_matricule();
    }

    @CommandHandler
    public void handle(UpdateInfractionCommand command) {
        AggregateLifecycle.apply(new InfractionUpdatedEvent(
                command.getId(),
                command.getDate(),
                command.getVitesse(),
                command.getMontant(),
                command.getRadarId(),
                command.getVehicule_matricule()
        ));
    }

    @EventSourcingHandler
    public void on(InfractionUpdatedEvent event) {
        this.id = event.getId();
        this.date = event.getDate();
        this.vitesse= event.getVitesse();
        this.montant=event.getMontant();
        this.radarId = event.getRadarId();
        this.vehicule_matricule= event.getVehicule_matricule();
    }
}
