package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "characters")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;
    private String name;
    private Integer age;
    private Float weight;
    private String history;
    private boolean deleted= Boolean.FALSE;

    // ManyToMany: Movies
    @ManyToMany(mappedBy = "movieCharacters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovieEntity> characterMovies = new ArrayList<>();

}
