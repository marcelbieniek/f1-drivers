package pl.edu.pg.eti.marcelbieniek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.marcelbieniek.dto.*;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.entity.Team;
import pl.edu.pg.eti.marcelbieniek.service.DriverService;
import pl.edu.pg.eti.marcelbieniek.service.TeamService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/teams")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<GetTeamsResponse> getTeams() {
        List<Team> all = teamService.findAll();
        Function<Collection<Team>, GetTeamsResponse> mapper = GetTeamsResponse.entityToDtoMapper();
        GetTeamsResponse response = mapper.apply(all);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetTeamResponse> getTeam(@PathVariable("name") String name) {
        return teamService.find(name)
                .map(value -> ResponseEntity.ok(GetTeamResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createTeam(@RequestBody CreateTeamRequest request, UriComponentsBuilder builder) {
        Team team = CreateTeamRequest
                .dtoToEntityMapper()
                .apply(request);

        team = teamService.create(team);

        return ResponseEntity.created(builder.pathSegment("api", "teams", "{name}")
                .buildAndExpand(team.getName()).toUri()).build();
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateTeam(@RequestBody UpdateTeamRequest request, @PathVariable("name") String name) {
        Optional<Team> team = teamService.find(name);

        if(team.isPresent()) {
            UpdateTeamRequest.dtoToEntityUpdater().apply(team.get(), request);
            teamService.update(team.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("name") String name) {
        Optional<Team> team = teamService.find(name);

        if(team.isPresent()) {
            teamService.delete(team.get().getName());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
