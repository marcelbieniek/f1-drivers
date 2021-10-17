package pl.edu.pg.eti.marcelbieniek.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.storage.Storage;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class DriverRepository implements Repository<Driver, Long> {

    private Storage storage;

    @Autowired
    public DriverRepository(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Optional<Driver> find(Long id) {
        return storage.findDriver(id);
    }

    @Override
    public List<Driver> findAll() {
        return storage.findAllDrivers();
    }

    @Override
    public void create(Driver entity) {
        storage.createDriver(entity);
    }

    @Override
    public void delete(Driver entity) {
        storage.deleteDriver(entity.getId());
    }

    @Override
    public void update(Driver entity) {
        storage.updateDriver(entity);
    }
}
