package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
//diferencio de los que se borraron y los que no
@Where(clause = "deleted=false")
@Table(name = "characters")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;
    private String name;
    @NotNull(message = "Age is required")
    private Integer age;
    @NotNull(message = "Weight is required")
    private Float weight;
    private String history;
    private boolean deleted= Boolean.FALSE;

    // ManyToMany: Movies
    @ManyToMany(mappedBy = "movieCharacters", cascade = CascadeType.ALL)
    private List<MovieEntity> characterMovies = new ArrayList<>();





}
