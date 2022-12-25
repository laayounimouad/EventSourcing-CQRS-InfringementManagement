package ma.laayouni.ImmatriculationService.querySide.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    @Id
    private String id;
    private String nom;
    private Date date_naissance;
    private String email;
    @OneToMany(mappedBy = "owner")
    private List<Vehicule> vehicule;
}
