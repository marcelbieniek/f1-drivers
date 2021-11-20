package pl.edu.pg.eti.marcelbieniek.team.event.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;
import pl.edu.pg.eti.marcelbieniek.team.event.dto.CreateTeamRequest;

@Repository
public class TeamEventRepository {

    private RestTemplate restTemplate;

    public TeamEventRepository(@Value("${f1drivers.drivers.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Team team) {
        restTemplate.delete("/teams/{team_name}", team.getName());
    }

    public void create(Team team) {
        restTemplate.postForLocation("/teams", CreateTeamRequest.entityToDtoMapper().apply(team));
    }
}
