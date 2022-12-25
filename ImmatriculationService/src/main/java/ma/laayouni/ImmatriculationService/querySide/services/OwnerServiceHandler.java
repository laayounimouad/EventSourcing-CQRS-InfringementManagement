package ma.laayouni.ImmatriculationService.querySide.services;

import events.owner.OwnerCreatedEvent;
import events.owner.OwnerUpdatedEvent;
import events.radar.RadarCreatedEvent;
import events.radar.RadarUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.laayouni.ImmatriculationService.querySide.entities.Owner;
import ma.laayouni.ImmatriculationService.querySide.repositories.OwnerRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import queries.owner.GetAllOwnersQuery;
import queries.radar.GetAllRadarsQuery;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class OwnerServiceHandler {
    private OwnerRepository ownerRepository;

    @EventHandler
    public void on(OwnerCreatedEvent event) {
        log.info("RadarCreatedEvent received");
        Owner owner = new Owner();
        owner.setId(event.getId());
        owner.setEmail(event.getEmail());
        owner.setDate_naissance(event.getDate_naissance());
        owner.setNom(event.getNom());
        ownerRepository.save(owner);
    }

    @EventHandler
    public void on(OwnerUpdatedEvent event) {
        log.info("RadarUpdatedEvent received");

        Owner owner = ownerRepository.findById(event.getId()).get();
        owner.setEmail(event.getEmail());
        owner.setDate_naissance(event.getDate_naissance());
        owner.setNom(event.getNom());
        ownerRepository.save(owner);
    }
    @QueryHandler
    public List<Owner> on(GetAllOwnersQuery query){
        return ownerRepository.findAll();
    }
}
