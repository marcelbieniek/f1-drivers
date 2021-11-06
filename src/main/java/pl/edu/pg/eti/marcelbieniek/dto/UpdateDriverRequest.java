package pl.edu.pg.eti.marcelbieniek.dto;

import lombok.*;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateDriverRequest {

    private String name;
    private int wins;
    private int championships;

    public static BiFunction<Driver, UpdateDriverRequest, Driver> dtoToEntityUpdater() {
        return (driver, request) -> {
            driver.setName(request.getName());
            driver.setWins(request.getWins());
            driver.setChampionships(request.getChampionships());
            return driver;
        };
    }
}
