package pl.edu.pg.eti.marcelbieniek.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.eti.marcelbieniek.entity.Team;
import pl.edu.pg.eti.marcelbieniek.storage.Storage;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class TeamRepository implements Repository<Team, String> {

    private Storage storage;

    @Autowired
    public TeamRepository(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Optional<Team> find(String id) {
        return storage.findTeam(id);
    }

    @Override
    public List<Team> findAll() {
        return storage.findAllTeams();
    }

    @Override
    public void create(Team entity) {
        storage.createTeam(entity);
    }

    @Override
    public void delete(Team entity) {
        throw new UnsupportedOperationException("Operation not supported!");
    }

    @Override
    public void update(Team entity) {
        throw new UnsupportedOperationException("Operation not supported!");
    }
}
