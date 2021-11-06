package pl.edu.pg.eti.marcelbieniek.dto;

import lombok.*;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.entity.Team;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateTeamRequest {

    private String name;
    private int wins;
    private int championships;

    public static BiFunction<Team, UpdateTeamRequest, Team> dtoToEntityUpdater() {
        return (team, request) -> {
            team.setName(request.getName());
            team.setWins(request.getWins());
            team.setChampionships(request.getChampionships());
            return team;
        };
    }
}
