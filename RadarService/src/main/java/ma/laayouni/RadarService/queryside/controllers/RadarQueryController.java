package ma.laayouni.RadarService.queryside.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.laayouni.RadarService.queryside.entities.Radar;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queries.radar.GetAllRadarsQuery;

import java.util.List;

@RestController
@RequestMapping("/query/radar")
@AllArgsConstructor
@Slf4j
public class RadarQueryController {
    private QueryGateway queryGateway;

    @GetMapping("/allRadars")
    public List<Radar> accountList() {
        List<Radar> response = queryGateway.query(new GetAllRadarsQuery(), ResponseTypes.multipleInstancesOf(Radar.class)).join();
        return response;
    }
}
