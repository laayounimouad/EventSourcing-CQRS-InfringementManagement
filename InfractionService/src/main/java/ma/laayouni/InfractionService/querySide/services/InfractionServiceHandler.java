package ma.laayouni.InfractionService.querySide.services;

import events.Infraction.InfractionCreatedEvent;
import events.Infraction.InfractionUpdatedEvent;
import events.radar.RadarCreatedEvent;
import events.radar.RadarUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.laayouni.InfractionService.querySide.entities.Infraction;
import ma.laayouni.InfractionService.querySide.entities.Vehicule;
import ma.laayouni.InfractionService.querySide.repositories.InfractionRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import queries.Infraction.GetAllInfractionsQuery;
import queries.Infraction.GetInfractionsByMatriculeQuery;
import queries.Infraction.GetInfractionsByOwnerIdQuery;
import queries.radar.GetAllRadarsQuery;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class InfractionServiceHandler {
    private InfractionRepository infractionRepository;
    private VehiculeRestClientService vehiculeRestClientService;

    @EventHandler
    public void on(InfractionCreatedEvent event) {
        log.info("InfractionCreatedEvent received");
        Infraction infraction = new Infraction();
        infraction.setId(event.getId());
        infraction.setDate(event.getDate());
        infraction.setMontant(event.getMontant());
        infraction.setRadarId(event.getRadarId());
        infraction.setVehicule_matricule(event.getVehicule_matricule());
        infractionRepository.save(infraction);
    }

    @EventHandler
    public void on(InfractionUpdatedEvent event) {
        log.info("InfractionUpdatedEvent received");

        Infraction infraction = infractionRepository.findById(event.getId()).get();
        infraction.setDate(event.getDate());
        infraction.setMontant(event.getMontant());
        infraction.setRadarId(event.getRadarId());
        infraction.setVehicule_matricule(event.getVehicule_matricule());
        infractionRepository.save(infraction);
    }
    @QueryHandler
    public List<Infraction> on(GetAllInfractionsQuery query){
        return infractionRepository.findAll();
    }


    @QueryHandler
    public List<Infraction> on(GetInfractionsByOwnerIdQuery query){
        return infractionRepository.findAll();
    }


    @QueryHandler
    public List<Infraction> on(GetInfractionsByMatriculeQuery query){
        return infractionRepository.findByVehicule_matricule(query.getMatricule());
    }

    @QueryHandler
    public Vehicule on(GetInfractionsByMatriculeQuery query){
        return vehiculeRestClientService.vehiculebyId(query.());
    }
}
