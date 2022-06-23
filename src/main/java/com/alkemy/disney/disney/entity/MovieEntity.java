package com.alkemy.disney.disney.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotNull(message = "is required")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    /*@PastOrPresent(message = "the year must be past or present")*/
    private LocalDate creationDate;


    @NotNull(message = "The rating is required")
    @Min(1)
    @Max(5)
    private int rating;


    private boolean deleted = Boolean.FALSE;


    //Many to Many between movie and character
    //El tipo de cascada de la relación será con las operaciones de persistir y mergear
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_characters",
            joinColumns= @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<CharacterEntity> movieCharacters = new ArrayList<>();

    //Buscar información
    @ManyToOne
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private GenreEntity genres;
    //guardar y actualizar// define la columna de genreid en la base de datos
    @Column(name = "genre_id", nullable = false)
    private Long genreId;



   /// CHARACTER
    public void addCharacterToMovie(CharacterEntity charToBeAdded) {

        this.movieCharacters.add(charToBeAdded);
    }

    public void removeCharacterFromMovie(CharacterEntity charToBeRemoved) {
        this.movieCharacters.remove(charToBeRemoved);
    }



}
