package pl.edu.pg.eti.marcelbieniek.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;
import pl.edu.pg.eti.marcelbieniek.team.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private TeamRepository repository;

    @Autowired
    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public Optional<Team> find(String name) {
        return repository.findById(name);
    }

    public List<Team> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Team create(Team team) {
        return repository.save(team);
    }

    @Transactional
    public void update(Team team) {
        repository.save(team);
    }

    @Transactional
    public void delete(String team) {
        repository.deleteById(team);
    }
}
