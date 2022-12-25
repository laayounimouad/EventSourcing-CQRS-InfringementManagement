package dtos.vehicule;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehiculeRequestDTO {
    private String id;
    private String matriculeNumber;
    private String marque;
    private String puissanceFiscale;
    private String modele;
    private String ownerId;
}
