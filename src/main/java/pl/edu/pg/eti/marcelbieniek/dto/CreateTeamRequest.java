package pl.edu.pg.eti.marcelbieniek.dto;

import lombok.*;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.entity.Team;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateTeamRequest {

    private String name;
    private int wins;
    private int championships;

    public static Function<CreateTeamRequest, Team> dtoToEntityMapper() {
        return request -> Team.builder()
                .name(request.getName())
                .wins(request.getWins())
                .championships(request.getChampionships())
                .build();
    }
}
