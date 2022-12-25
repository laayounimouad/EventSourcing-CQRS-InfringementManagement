package ma.laayouni.InfractionService.querySide.repositories;

import ma.laayouni.InfractionService.querySide.entities.Infraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfractionRepository  extends JpaRepository<Infraction,String> {
    List<Infraction> findByVehicule_matricule(String matricule);
}
