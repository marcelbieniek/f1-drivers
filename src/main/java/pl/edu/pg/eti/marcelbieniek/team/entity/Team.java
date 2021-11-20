package pl.edu.pg.eti.marcelbieniek.team.entity;

import lombok.*;

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
}
