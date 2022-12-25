package ma.laayouni.ImmatriculationService.commandSide.controllers;

import commands.owner.CreateOwnerCommand;
import commands.owner.UpdateOwnerCommand;
import commands.vehicule.CreateVehiculeCommand;
import commands.vehicule.UpdateVehiculeCommand;
import dtos.owner.CreateOwnerRequestDTO;
import dtos.owner.UpdateOwnerRequestDTO;
import dtos.vehicule.CreateVehiculeRequestDTO;
import dtos.vehicule.UpdateVehiculeRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/vehicule")
@AllArgsConstructor
public class VehiculeCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createVehicule(@RequestBody CreateVehiculeRequestDTO request){
        CompletableFuture<String> response= commandGateway.send(new CreateVehiculeCommand(
                UUID.randomUUID().toString(),
               request.getMatriculeNumber(),
                request.getMarque(),
                request.getPuissanceFiscale(),
                request.getModele(),
                request.getOwnerId()
        ));
        return response;
    }

    @PutMapping("/update")
    public CompletableFuture<String> updateVehicule(@RequestBody UpdateVehiculeRequestDTO request){
        return commandGateway.send(new UpdateVehiculeCommand(
                request.getId(),
                request.getMatriculeNumber(),
                request.getMarque(),
                request.getPuissanceFiscale(),
                request.getModele(),
                request.getOwnerId()
        ));
    }
}

