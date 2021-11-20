package pl.edu.pg.eti.marcelbieniek.driver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.marcelbieniek.driver.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndTeam(Long id, Team team);

    List<Driver> findAllByTeam(Team team);
}
