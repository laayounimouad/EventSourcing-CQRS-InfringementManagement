package ma.laayouni.InfractionService.querySide.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {
    private String id;
    private String matriculeNumber;
    private String marque;
    private String puissanceFiscale;
    private String modele;
}
