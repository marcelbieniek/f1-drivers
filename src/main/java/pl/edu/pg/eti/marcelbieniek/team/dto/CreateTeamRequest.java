package pl.edu.pg.eti.marcelbieniek.team.dto;

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
public class CreateTeamRequest {

    private String name;

    public static Function<CreateTeamRequest, Team> dtoToEntityMapper() {
        return request -> Team.builder()
                .name(request.getName())
                .build();
    }
}
