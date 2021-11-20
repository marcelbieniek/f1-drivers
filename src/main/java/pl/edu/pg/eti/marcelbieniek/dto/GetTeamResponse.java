package pl.edu.pg.eti.marcelbieniek.dto;

import lombok.*;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTeamResponse {

    private String name;
    private int wins;
    private int championships;

    public static Function<Team, GetTeamResponse> entityToDtoMapper() {
        return team -> GetTeamResponse.builder()
                .name(team.getName())
                .wins(team.getWins())
                .championships(team.getChampionships())
                .build();
    }
}
