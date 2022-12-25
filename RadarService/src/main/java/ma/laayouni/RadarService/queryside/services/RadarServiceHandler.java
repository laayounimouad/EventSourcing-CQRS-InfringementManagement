package ma.laayouni.RadarService.queryside.services;

import events.radar.RadarCreatedEvent;
import events.radar.RadarUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.laayouni.RadarService.queryside.entities.Radar;
import ma.laayouni.RadarService.queryside.repositories.RadarRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import queries.radar.GetAllRadarsQuery;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class RadarServiceHandler {
    private RadarRepository radarRepository;

    @EventHandler
    public void on(RadarCreatedEvent event) {
        log.info("RadarCreatedEvent received");
        Radar radar = new Radar();
        radar.setId(event.getId());
        radar.setLatitude(event.getLatitude());
        radar.setLongitude(event.getLongitude());
        radar.setMax_vitesse(event.getMax_vitesse());
        radarRepository.save(radar);
    }

    @EventHandler
    public void on(RadarUpdatedEvent event) {
        log.info("RadarUpdatedEvent received");

        Radar radar = radarRepository.findById(event.getId()).get();
        radar.setLatitude(event.getLatitude());
        radar.setLongitude(event.getLongitude());
        radar.setMax_vitesse(event.getMax_vitesse());
        radarRepository.save(radar);
    }
    @QueryHandler
    public List<Radar> on(GetAllRadarsQuery query){
        return radarRepository.findAll();
    }
}
