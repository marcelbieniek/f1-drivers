package pl.edu.pg.eti.marcelbieniek.storage;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.entity.Team;
import pl.edu.pg.eti.marcelbieniek.serialization.Cloner;

import java.util.*;
import java.util.stream.Collectors;

@Log
@Component
public class Storage {

    private Set<Driver> drivers = new HashSet<>();

    private Set<Team> teams = new HashSet<>();

    public synchronized List<Driver> findAllDrivers() {
        return drivers.stream()
                .map(Cloner::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Driver> findDriver(Long id) {
        return drivers.stream()
                .filter(driver -> driver.getId().equals(id))
                .findFirst()
                .map(Cloner::clone);
    }

    public synchronized void createDriver(Driver driver) throws IllegalArgumentException {
        driver.setId(findAllDrivers().stream()
                .mapToLong(Driver::getId)
                .max().orElse(0) + 1);
        drivers.add(Cloner.clone(driver));
    }

    public synchronized void updateDriver(Driver driver) throws IllegalArgumentException {
        findDriver(driver.getId()).ifPresentOrElse(
                original -> {
                    drivers.remove(original);
                    drivers.add(Cloner.clone(driver));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("Driver with id \"%d\" does not exist", driver.getId()));
                });
    }

    public synchronized void deleteDriver(Long id) throws IllegalArgumentException {
        findDriver(id).ifPresentOrElse(
                original -> drivers.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("Driver with id \"%d\" does not exist", id));
                });
    }

    public synchronized List<Team> findAllTeams() {
        return new ArrayList<>(teams);
    }

    public synchronized Optional<Team> findTeam(String name) {
        return teams.stream()
                .filter(team -> team.getName().equals(name))
                .findFirst()
                .map(Cloner::clone);
    }

    public synchronized void createTeam(Team team) throws IllegalArgumentException {
        findTeam(team.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("Team \"%s\" already exists", team.getName()));
                },
                () -> teams.add(Cloner.clone(team)));
    }
}
