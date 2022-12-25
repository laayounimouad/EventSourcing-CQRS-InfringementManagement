package ma.laayouni.ImmatriculationService.querySide.repositories;


import ma.laayouni.ImmatriculationService.querySide.entities.Owner;
import ma.laayouni.ImmatriculationService.querySide.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository extends JpaRepository<Vehicule,String> {
}
