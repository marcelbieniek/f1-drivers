package pl.edu.pg.eti.marcelbieniek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.marcelbieniek.entity.Team;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    //Optional<Team> findByName(String name);
}
