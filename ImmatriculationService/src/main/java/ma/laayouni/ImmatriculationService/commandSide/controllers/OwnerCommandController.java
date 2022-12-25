package ma.laayouni.ImmatriculationService.commandSide.controllers;

import commands.owner.CreateOwnerCommand;
import commands.owner.UpdateOwnerCommand;
import dtos.owner.CreateOwnerRequestDTO;
import dtos.owner.UpdateOwnerRequestDTO;
import dtos.radar.UpdateRadarRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/owner")
@AllArgsConstructor
public class OwnerCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createOwner(@RequestBody CreateOwnerRequestDTO request){
        CompletableFuture<String> response= commandGateway.send(new CreateOwnerCommand(
                UUID.randomUUID().toString(),
                request.getNom(),
                request.getDate_naissance(),
                request.getEmail()
        ));
        return response;
    }

    @PutMapping("/update")
    public CompletableFuture<String> updateRadar(@RequestBody UpdateOwnerRequestDTO request){
        return commandGateway.send(new UpdateOwnerCommand(
                request.getId(),
                request.getNom(),
                request.getDate_naissance(),
                request.getEmail()
        ));
    }
}
