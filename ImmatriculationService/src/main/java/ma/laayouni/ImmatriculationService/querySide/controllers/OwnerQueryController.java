package ma.laayouni.ImmatriculationService.querySide.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.laayouni.ImmatriculationService.querySide.entities.Owner;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queries.owner.GetAllOwnersQuery;
import queries.radar.GetAllRadarsQuery;

import java.util.List;

@RestController
@RequestMapping("/queries/owner")
@AllArgsConstructor
@Slf4j
public class OwnerQueryController {
    private QueryGateway queryGateway;

    @GetMapping("/allOwners")
    public List<Owner> ownersList() {
        List<Owner> response = queryGateway.query(new GetAllOwnersQuery(), ResponseTypes.multipleInstancesOf(Owner.class)).join();
        return response;
    }
}
