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
import pl.edu.pg.eti.marcelbieniek.entity.Team;
import pl.edu.pg.eti.marcelbieniek.service.DriverService;
import pl.edu.pg.eti.marcelbieniek.service.TeamService;

import java.util.Optional;

@RestController
@RequestMapping("api/teams/{team_name}/drivers")
public class TeamDriverController {

    private DriverService driverService;
    private TeamService teamService;

    @Autowired
    public TeamDriverController(DriverService driverService, TeamService teamService) {
        this.driverService = driverService;
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<GetDriversResponse> getDrivers(@PathVariable("team_name") String team_name) {
        Optional<Team> team = teamService.find(team_name);

        return team.map(value -> ResponseEntity.ok(GetDriversResponse.entityToDtoMapper().apply(driverService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetDriverResponse> getDriver(@PathVariable("team_name") String team_name, @PathVariable("id") long id) {
        return driverService.find(id, team_name)
                .map(value -> ResponseEntity.ok(GetDriverResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createDriver(@PathVariable("team_name") String team_name,
                                                @RequestBody CreateDriverRequest request,
                                                UriComponentsBuilder builder) {
        Optional<Team> team = teamService.find(team_name);
        if (team.isPresent()) {
            Driver driver = CreateDriverRequest
                    .dtoToEntityMapper(team::get)
                    .apply(request);

            driver = driverService.create(driver);

            return ResponseEntity.created(builder.pathSegment("api", "teams", "{team_name}", "drivers", "{id}")
                    .buildAndExpand(team.get().getName(), driver.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateDriver(@PathVariable("team_name") String team_name,
                                                @RequestBody UpdateDriverRequest request,
                                                @PathVariable("id") long id) {
        Optional<Driver> driver = driverService.find(id, team_name);
        if (driver.isPresent()) {
            UpdateDriverRequest.dtoToEntityUpdater().apply(driver.get(), request);
            driverService.update(driver.get());

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable("team_name") String team_name,
                                                @PathVariable("id") long id) {
        Optional<Driver> driver = driverService.find(id, team_name);
        if (driver.isPresent()) {
            driverService.delete(driver.get().getId());

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
