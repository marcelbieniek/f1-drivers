package pl.edu.pg.eti.marcelbieniek.driver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.marcelbieniek.driver.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;
import pl.edu.pg.eti.marcelbieniek.driver.repository.DriverRepository;
import pl.edu.pg.eti.marcelbieniek.team.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private DriverRepository driverRepository;
    private TeamRepository teamRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, TeamRepository teamRepository) {
        this.driverRepository = driverRepository;
        this.teamRepository = teamRepository;
    }

    public Optional<Driver> find(Long id) {
        return driverRepository.findById(id);
    }

    public Optional<Driver> find(Long id, Team team) {
        return driverRepository.findByIdAndTeam(id, team);
    }

    public Optional<Driver> find(Long id, String team_name) {
        Optional<Team> team = teamRepository.findById(team_name);

        if(team.isPresent()) {
            return driverRepository.findByIdAndTeam(id, team.get());
        }
        else {
            return Optional.empty();
        }
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public List<Driver> findAll(Team team) {
        return driverRepository.findAllByTeam(team);
    }

    @Transactional
    public Driver create(Driver driver) {
        return driverRepository.save(driver);
    }

    @Transactional
    public void update(Driver driver) {
        driverRepository.save(driver);
    }

    @Transactional
    public void delete(Long driver) {
        driverRepository.deleteById(driver);
    }
}
