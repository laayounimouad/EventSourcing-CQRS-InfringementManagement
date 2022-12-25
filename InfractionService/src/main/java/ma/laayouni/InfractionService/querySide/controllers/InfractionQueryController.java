package ma.laayouni.InfractionService.querySide.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.laayouni.InfractionService.querySide.entities.Infraction;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queries.Infraction.GetInfractionsByMatriculeQuery;
import queries.Infraction.GetInfractionsByOwnerIdQuery;
import queries.Infraction.GetAllInfractionsQuery;

import java.util.List;

@RestController
@RequestMapping("/queries/infraction")
@AllArgsConstructor
@Slf4j
public class InfractionQueryController {
    private QueryGateway queryGateway;

    @GetMapping("/allInfractions")
    public List<Infraction> infractionsList() {
        List<Infraction> response = queryGateway.query(new GetAllInfractionsQuery(), ResponseTypes.multipleInstancesOf(Infraction.class)).join();
        return response;
    }

    @GetMapping("/byOwnerId/{ownerId}")
    public List<Infraction> infractionsByOwnerIdList(@PathVariable String ownerId){
        List<Infraction> response = queryGateway.query(new GetInfractionsByOwnerIdQuery(ownerId), ResponseTypes.multipleInstancesOf(Infraction.class)).join();
        return response;
    }

    @GetMapping("/byMatricule/{matricule}")
    public List<Infraction> infractionsByMatriculeList(@PathVariable String matricule){
        List<Infraction> response = queryGateway.query(new GetInfractionsByMatriculeQuery(matricule), ResponseTypes.multipleInstancesOf(Infraction.class)).join();
        return response;
    }
}
