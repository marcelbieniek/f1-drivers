package pl.edu.pg.eti.marcelbieniek.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Entity
@Table(name = "drivers")
public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "team")
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
