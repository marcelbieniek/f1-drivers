package pl.edu.pg.eti.marcelbieniek.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Driver implements Serializable {

    private Long id;

    private String name;
    private Team team;
    private int wins;
    private int championships;

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team=" + team.getName() +
                ", wins=" + wins +
                ", championships=" + championships +
                '}';
    }
}
