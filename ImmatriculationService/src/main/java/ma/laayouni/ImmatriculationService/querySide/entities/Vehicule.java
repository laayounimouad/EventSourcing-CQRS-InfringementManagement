package ma.laayouni.ImmatriculationService.querySide.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {
    @Id
    private String id;
    private String matriculeNumber;
    private String marque;
    private String puissanceFiscale;
    private String modele;
    @ManyToOne
    private Owner owner;
}
