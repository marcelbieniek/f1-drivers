package pl.edu.pg.eti.marcelbieniek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.marcelbieniek.dto.CreateDriverRequest;
import pl.edu.pg.eti.marcelbieniek.dto.GetDriverResponse;
import pl.edu.pg.eti.marcelbieniek.dto.GetDriversResponse;
import pl.edu.pg.eti.marcelbieniek.dto.UpdateDriverRequest;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.service.DriverService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/drivers")
public class DriverController {

    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<GetDriversResponse> getDrivers() {
        List<Driver> all = driverService.findAll();
        Function<Collection<Driver>, GetDriversResponse> mapper = GetDriversResponse.entityToDtoMapper();
        GetDriversResponse response = mapper.apply(all);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetDriverResponse> getDriver(@PathVariable("id") long id) {
        return driverService.find(id)
                .map(value -> ResponseEntity.ok(GetDriverResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createDriver(@RequestBody CreateDriverRequest request, UriComponentsBuilder builder) {
        Driver driver = CreateDriverRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);

        driver = driverService.create(driver);

        return ResponseEntity.created(builder.pathSegment("api", "drivers", "{id}")
                .buildAndExpand(driver.getId()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateDriver(@RequestBody UpdateDriverRequest request, @PathVariable("id") long id) {
        Optional<Driver> driver = driverService.find(id);

        if(driver.isPresent()) {
            UpdateDriverRequest.dtoToEntityUpdater().apply(driver.get(), request);
            driverService.update(driver.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable("id") long id) {
        Optional<Driver> driver = driverService.find(id);

        if(driver.isPresent()) {
            driverService.delete(driver.get().getId());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
