package ma.laayouni.ImmatriculationService.querySide.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.laayouni.ImmatriculationService.querySide.entities.Owner;
import ma.laayouni.ImmatriculationService.querySide.entities.Vehicule;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queries.owner.GetAllOwnersQuery;
import queries.vehicule.GetAllVehiculesQuery;

import java.util.List;

@RestController
@RequestMapping("/queries/vehicule")
@AllArgsConstructor
@Slf4j
public class VehiculeQueryController {
    private QueryGateway queryGateway;

    @GetMapping("/allVehicules")
    public List<Vehicule> vehiculesList() {
        List<Vehicule> response = queryGateway.query(new GetAllVehiculesQuery(), ResponseTypes.multipleInstancesOf(Vehicule.class)).join();
        return response;
    }
}
