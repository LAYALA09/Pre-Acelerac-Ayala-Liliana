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

    @NotNull(message = "Image is required")
    private String image;
    @NotNull(message = "Title is required")
    private String title;
    @Column(name = "creation_date")
    @NotNull(message = "is required")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
   /* @PastOrPresent(message = "the year must be past or present")*/
    private LocalDate creationDate;
    @NotNull(message = "The rating is required")
    @Min(1)
    @Max(5)
    private int rating;
    private boolean deleted = Boolean.FALSE;

    //Many to Many between movie and character
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })

    @JoinTable(
            name = "movie_characters",
            joinColumns= @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<CharacterEntity> movieCharacters = new ArrayList<>();


    // El Objeto GeneroEntity, este atributo será solo para traer la información del Genero
    // Se ha modificado el CascadeType.ALL para no eliminar el género si se elimina una película
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private GenreEntity genre;

    //guardar y actualizar// define la columna de genreId en la base de datos
    @Column(name = "genre_id", nullable = false)
    private Long genreId;



   // CHARACTER
    public void addCharacterToMovie(CharacterEntity charToBeAdded) {

        this.movieCharacters.add(charToBeAdded);
    }

}
