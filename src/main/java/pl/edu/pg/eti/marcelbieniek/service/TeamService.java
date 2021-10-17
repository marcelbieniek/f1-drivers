package pl.edu.pg.eti.marcelbieniek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.marcelbieniek.entity.Team;
import pl.edu.pg.eti.marcelbieniek.repository.TeamRepository;

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
        return repository.find(name);
    }

    public List<Team> findAll() {
        return repository.findAll();
    }

    public void create(Team team) {
        repository.create(team);
    }
}
