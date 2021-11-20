package pl.edu.pg.eti.marcelbieniek.dto;

import lombok.*;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateDriverRequest {

    private String name;
    private String team;
    private int wins;
    private int championships;

//    public static Function<CreateDriverRequest, Driver> dtoToEntityMapper(Function<String, Team> teamFunction) {
//        return request -> Driver.builder()
//                .name(request.getName())
//                .team(teamFunction.apply(request.getTeam()))
//                .wins(request.getWins())
//                .championships(request.getChampionships())
//                .build();
//    }

    public static Function<CreateDriverRequest, Driver> dtoToEntityMapper(Supplier<Team> teamSupplier) {
        return request -> Driver.builder()
                .name(request.getName())
                .team(teamSupplier.get())
                .wins(request.getWins())
                .championships(request.getChampionships())
                .build();
    }
}
