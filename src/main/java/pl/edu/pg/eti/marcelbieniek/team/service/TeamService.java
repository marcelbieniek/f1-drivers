package pl.edu.pg.eti.marcelbieniek.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;
import pl.edu.pg.eti.marcelbieniek.team.event.repository.TeamEventRepository;
import pl.edu.pg.eti.marcelbieniek.team.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private TeamRepository repository;
    private TeamEventRepository eventRepository;

    @Autowired
    public TeamService(TeamRepository repository, TeamEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Team> find(String name) {
        return repository.findById(name);
    }

    public List<Team> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(Team team) {
        repository.save(team);
        eventRepository.create(team);
    }

//    @Transactional
//    public void update(Team team) {
//        repository.save(team);
//    }

    @Transactional
    public void delete(Team team) {
        eventRepository.delete(team);
        repository.delete(team);
    }
}
