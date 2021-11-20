package pl.edu.pg.eti.marcelbieniek.driver.dto;

import lombok.*;
import pl.edu.pg.eti.marcelbieniek.driver.entity.Driver;

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
    private int wins;
    private int championships;

    public static Function<Driver, GetDriverResponse> entityToDtoMapper() {
        return driver -> GetDriverResponse.builder()
                .id(driver.getId())
                .name(driver.getName())
                .wins(driver.getWins())
                .championships(driver.getChampionships())
                .build();
    }
}
