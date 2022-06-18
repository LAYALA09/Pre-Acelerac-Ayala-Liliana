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
    private String creationDate;
    private Float rating;
    private Long genreId;
    private List<CharacterDTO> movieCharacters;








}
