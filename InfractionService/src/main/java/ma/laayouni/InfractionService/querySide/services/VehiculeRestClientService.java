package ma.laayouni.InfractionService.querySide.services;

import ma.laayouni.InfractionService.querySide.entities.Vehicule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "IMMATRICULATION-SERVICE")
public interface VehiculeRestClientService {

    @GetMapping("/queries/vehicule/byId/{id}")
    Vehicule vehiculebyId(@PathVariable Long id);

}
