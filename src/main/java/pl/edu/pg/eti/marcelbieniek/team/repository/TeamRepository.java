package pl.edu.pg.eti.marcelbieniek.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    //Optional<Team> findByName(String name);
}
