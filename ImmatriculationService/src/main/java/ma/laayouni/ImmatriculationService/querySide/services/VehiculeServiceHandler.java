package ma.laayouni.ImmatriculationService.querySide.services;

import events.owner.OwnerCreatedEvent;
import events.owner.OwnerUpdatedEvent;
import events.vehicule.VehiculeCreatedEvent;
import events.vehicule.VehiculeUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.laayouni.ImmatriculationService.querySide.entities.Owner;
import ma.laayouni.ImmatriculationService.querySide.entities.Vehicule;
import ma.laayouni.ImmatriculationService.querySide.repositories.OwnerRepository;
import ma.laayouni.ImmatriculationService.querySide.repositories.VehiculeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import queries.owner.GetAllOwnersQuery;
import queries.vehicule.GetAllVehiculesQuery;
import queries.vehicule.GetVehiculesByOwnerQuery;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class VehiculeServiceHandler {
    private VehiculeRepository vehiculeRepository;
    private OwnerRepository ownerRepository;

    @EventHandler
    public void on(VehiculeCreatedEvent event) {
        log.info("VehiculeCreatedEvent received");
        Vehicule vehicule = new Vehicule();
        vehicule.setId(event.getId());
        vehicule.setMarque(event.getMarque());
        vehicule.setMatriculeNumber(event.getMatriculeNumber());
        vehicule.setModele(event.getModele());
        vehicule.setPuissanceFiscale(event.getPuissanceFiscale());
        vehicule.setOwner(ownerRepository.findById(event.getOwnerId()).get());
        vehiculeRepository.save(vehicule);
    }

    @EventHandler
    public void on(VehiculeUpdatedEvent event) {
        log.info("VehiculeUpdatedEvent received");

        Vehicule vehicule = vehiculeRepository.findById(event.getId()).get();
        vehicule.setMarque(event.getMarque());
        vehicule.setMatriculeNumber(event.getMatriculeNumber());
        vehicule.setModele(event.getModele());
        vehicule.setPuissanceFiscale(event.getPuissanceFiscale());
        vehicule.setOwner(ownerRepository.findById(event.getOwnerId()).get());
        vehiculeRepository.save(vehicule);
    }
    @QueryHandler
    public List<Vehicule> on(GetAllVehiculesQuery query){
        return vehiculeRepository.findAll();
    }

    @QueryHandler
    public List<Vehicule> on(GetVehiculesByOwnerQuery query){
        return vehiculeRepository.findVehiculeByOwnerId(query.getOwnerId());
    }
}
