package pl.edu.pg.eti.marcelbieniek.samples;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;
import pl.edu.pg.eti.marcelbieniek.service.DriverService;
import pl.edu.pg.eti.marcelbieniek.team.service.TeamService;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Component
public class SampleData {

    private final DriverService driverService;

    private final TeamService teamService;

    @Autowired
    public SampleData(DriverService driverService, TeamService teamService) {
        this.driverService = driverService;
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

        Driver leclerc = Driver.builder()
                .name("Charles Leclerc")
                .team(ferrari)
                .wins(2)
                .championships(0)
                .build();

        Driver hamilton = Driver.builder()
                .name("Lewis Hamilton")
                .team(mercedes)
                .wins(100)
                .championships(7)
                .build();

        Driver norris = Driver.builder()
                .name("Lando Norris")
                .team(mclaren)
                .wins(0)
                .championships(0)
                .build();

        Driver verstappen = Driver.builder()
                .name("Max Verstappen")
                .team(redbull)
                .wins(17)
                .championships(0)
                .build();

        driverService.create(leclerc);
        driverService.create(hamilton);
        driverService.create(norris);
        driverService.create(verstappen);
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }
}
