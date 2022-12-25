package ma.laayouni.RadarService.commandside.aggrerates;

import commands.radar.CreateRadarCommand;
import commands.radar.UpdateRadarCommand;
import events.radar.RadarCreatedEvent;
import events.radar.RadarUpdatedEvent;
import exceptions.EntryNegativeException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class RadarAggregate {
    @AggregateIdentifier
    private String id;
    private double max_vitesse;
    private double longitude;
    private double latitude;

    public RadarAggregate() {
    }


    @CommandHandler
    public RadarAggregate(CreateRadarCommand command){
        if(command.getMax_vitesse()<0) throw new EntryNegativeException("speed should be positive");
        AggregateLifecycle.apply(new RadarCreatedEvent(
                command.getId(),
                command.getMax_vitesse(),
                command.getLongitude(),
                command.getLatitude()
        ));
    }

    @EventSourcingHandler
    public void on(RadarCreatedEvent event){
        this.id=event.getId();
        this.latitude= event.getLatitude();
        this.longitude= event.getLongitude();
        this.max_vitesse=event.getMax_vitesse();
    }

    @CommandHandler
    public void handle(UpdateRadarCommand command){
        if(command.getMax_vitesse()<0) throw new EntryNegativeException("speed should be positive");
        AggregateLifecycle.apply(new RadarUpdatedEvent(
                command.getId(),
                command.getMax_vitesse(),
                command.getLongitude(),
                command.getLatitude()
        ));
    }
    @EventSourcingHandler
    public void on(RadarUpdatedEvent event){
        this.latitude= event.getLatitude();
        this.longitude= event.getLongitude();
        this.max_vitesse=event.getMax_vitesse();
    }

}
