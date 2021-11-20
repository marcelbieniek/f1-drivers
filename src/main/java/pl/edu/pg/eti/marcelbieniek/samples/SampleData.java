package pl.edu.pg.eti.marcelbieniek.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;
import pl.edu.pg.eti.marcelbieniek.team.service.TeamService;

import javax.annotation.PostConstruct;

@Component
public class SampleData {

    private final TeamService teamService;

    @Autowired
    public SampleData(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostConstruct
    private synchronized void init() {
        Team ferrari = Team.builder()
                .name("scuderia_ferrari")
                .wins(239)
                .championships(16)
                .build();

        Team mercedes = Team.builder()
                .name("mercedes_amg_petronas")
                .wins(112)
                .championships(7)
                .build();

        Team mclaren = Team.builder()
                .name("mclaren_f1")
                .wins(183)
                .championships(8)
                .build();

        Team redbull = Team.builder()
                .name("red_bull_racing")
                .wins(72)
                .championships(4)
                .build();

        teamService.create(ferrari);
        teamService.create(mercedes);
        teamService.create(mclaren);
        teamService.create(redbull);
    }
}
