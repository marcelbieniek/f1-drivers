package pl.edu.pg.eti.marcelbieniek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.repository.DriverRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private DriverRepository repository;

    @Autowired
    public DriverService(DriverRepository repository) {
        this.repository = repository;
    }

    public Optional<Driver> find(Long id) {
        return repository.find(id);
    }

    public List<Driver> findAll() {
        return repository.findAll();
    }

    public void create(Driver driver) {
        repository.create(driver);
    }

    public void update(Driver driver) {
        repository.update(driver);
    }

    public void delete(Long driver) {
        repository.delete(repository.find(driver).orElseThrow());
    }
}
