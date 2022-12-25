package ma.laayouni.RadarService.commandside.controllers;
import commands.radar.CreateRadarCommand;
import commands.radar.UpdateRadarCommand;
import dtos.radar.CreateRadarRequestDTO;
import dtos.radar.UpdateRadarRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/radar")
@AllArgsConstructor
public class RadarCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createRadar(@RequestBody CreateRadarRequestDTO request){
        CompletableFuture<String> response= commandGateway.send(new CreateRadarCommand(
                UUID.randomUUID().toString(),
                request.getMax_vitesse(),
                request.getLongitude(),
                request.getLatitude()
        ));
        return response;
    }

    @PostMapping("/update")
    public CompletableFuture<String> updateRadar(@RequestBody UpdateRadarRequestDTO request){
        return commandGateway.send(new UpdateRadarCommand(
                request.getId(),
                request.getMax_vitesse(),
                request.getLongitude(),
                request.getLatitude()
        ));
    }
}
