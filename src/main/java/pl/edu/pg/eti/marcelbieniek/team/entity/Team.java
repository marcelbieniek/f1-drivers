package pl.edu.pg.eti.marcelbieniek.team.entity;

import lombok.*;
import pl.edu.pg.eti.marcelbieniek.entity.Driver;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "teams")
public class Team implements Serializable {

    @Id
    private String name;
    private int wins;
    private int championships;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Driver> drivers;
}
