package pl.edu.pg.eti.marcelbieniek.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Team implements Serializable {

    private String name;
    private int wins;
    private int championships;
}
