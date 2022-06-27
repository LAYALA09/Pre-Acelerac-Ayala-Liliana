package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotNull(message = "Image is required")
    private String image;
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Age is required")
    private Integer age;
    @NotNull(message = "Weight is required")
    private float weight;
    @NotBlank(message = "History is required")
    private String history;
    private boolean deleted= Boolean.FALSE;

    // ManyToMany: Movies
    @ManyToMany(mappedBy = "movieCharacters", cascade = CascadeType.ALL)
    private List<MovieEntity> characterMovies = new ArrayList<>();





}
