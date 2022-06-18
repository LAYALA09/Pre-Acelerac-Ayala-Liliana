package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "movies")
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    //Atributos

    private String image;

    private String title;


    @Column(name = "creation_date")
    private LocalDate creationDate;


    private Float rating;

    private boolean deleted = Boolean.FALSE;


    //Many to Many between movie and character
    //El tipo de cascada de la relación será con las operaciones de persistir y mergear
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    // Definimos el nombre de la tabla intermedia y los nombres de ambas columnas
    @JoinTable(
            name = "movies_characters",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<CharacterEntity> movieCharacters = new ArrayList();

    //buscar información
    //FetchType.EAGER=Inicialización tipo temprana, quiere decir que la información q pida de Movie viene con su género
    @ManyToOne(fetch = FetchType.EAGER,cascade={ CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinColumn (name = "genre_id", insertable=false, updatable = false )
    private GenreEntity movieGenres;



    //guardar y actualizar// define la columna de genreid en la base de datos
    @Column(name="genre_id", nullable= false)
    private Long genreId;


    // --- Methods --- //
    // Characters//
    public void addCharacterToMovie(CharacterEntity charToBeAdded) {
        this.movieCharacters.add(charToBeAdded);
    }

    public void removeCharacterFromMovie(CharacterEntity charToBeRemoved) {
        this.movieCharacters.remove(charToBeRemoved);
    }





}
