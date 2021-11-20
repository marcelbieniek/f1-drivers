package pl.edu.pg.eti.marcelbieniek.driver.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetDriversResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Driver {

        private Long id;

        private String name;
    }

    @Singular
    private List<Driver> drivers;

    public static Function<Collection<pl.edu.pg.eti.marcelbieniek.driver.entity.Driver>, GetDriversResponse> entityToDtoMapper() {
        return drivers -> {
            GetDriversResponseBuilder response = GetDriversResponse.builder();

            drivers.stream()
                    .map(driver -> Driver.builder()
                            .id(driver.getId())
                            .name(driver.getName())
                            .build())
                    .forEach(response::driver);
            return response.build();
        };
    }
}
