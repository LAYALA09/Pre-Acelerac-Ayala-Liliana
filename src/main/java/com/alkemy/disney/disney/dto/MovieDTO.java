package com.alkemy.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class MovieDTO {

    private Long id;
    private String image;
    private String title;
    @Column(name= "date_creation")
    @DateTimeFormat(pattern = "YYYY/MM/dd")
    private LocalDate creationDate;
    private Float rating;
    private Long genreId;
    private List<CharacterDTO> characters;
    private List<GenreDTO> genres;







}
