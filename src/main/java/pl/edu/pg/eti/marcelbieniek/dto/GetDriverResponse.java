package pl.edu.pg.eti.marcelbieniek.dto;

import lombok.*;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetDriverResponse {

    private Long id;
    private String name;
    private String team;
    private int wins;
    private int championships;

    public static Function<Driver, GetDriverResponse> entityToDtoMapper() {
        return driver -> GetDriverResponse.builder()
                .id(driver.getId())
                .name(driver.getName())
                .team(driver.getTeam().getName())
                .wins(driver.getWins())
                .championships(driver.getChampionships())
                .build();
    }
}
