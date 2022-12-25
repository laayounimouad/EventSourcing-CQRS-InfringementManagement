package ma.laayouni.InfractionService.commandSide.controllers;

import commands.infraction.CreateInfractionCommand;
import commands.infraction.UpdateInfractionCommand;
import dtos.infraction.CreateInfractionRequestDTO;
import dtos.infraction.UpdateInfractionRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/infraction/commands")
@AllArgsConstructor
public class InfractionCommandController {
    private final CommandGateway commandGateway;
    private final EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createInfraction(@RequestBody CreateInfractionRequestDTO request) {
        return commandGateway.send(new CreateInfractionCommand(
                UUID.randomUUID().toString(),
                request.getDate(),
                request.getVitesse(),
                request.getMontant(),
                request.getRadarId(),
                request.getVehicule_matricule()
        ));
    }

    @PutMapping("/update")
    public CompletableFuture<String> updateInfraction(@RequestBody UpdateInfractionRequestDTO request) {
        return commandGateway.send(new UpdateInfractionCommand(
                request.getId(),
                request.getDate(),
                request.getVitesse(),
                request.getMontant(),
                request.getRadarId(),
                request.getVehicule_matricule()
        ));
    }
}
