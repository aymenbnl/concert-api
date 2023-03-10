package api.repositories;

import api.entities.Concert;
import api.entities.Soiree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {
    List<Optional<Concert>> findBySoiree(Soiree soiree);
    List<Optional<Concert>> findByIdGroupe(long id_groupe);

}
