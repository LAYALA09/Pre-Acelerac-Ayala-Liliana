package com.alkemy.disney.disney.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table (name = "genre")
@Entity
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @OneToOne(mappedBy = "genres")
    private MovieEntity movies;


}
