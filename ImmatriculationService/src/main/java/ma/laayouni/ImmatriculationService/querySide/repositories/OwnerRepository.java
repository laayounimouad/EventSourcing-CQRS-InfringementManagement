package ma.laayouni.ImmatriculationService.querySide.repositories;


import ma.laayouni.ImmatriculationService.querySide.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,String> {
}
