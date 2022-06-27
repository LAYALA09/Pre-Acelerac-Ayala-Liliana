package com.alkemy.disney.disney.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Setter
@Getter
public class MovieDTO {

    private Long id;
    @NotNull(message = "Image is required")
    private String image;
    @NotNull(message = "Title is required")
    private String title;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "Creation date is required")
    //@PastOrPresent es para validar que la fecha que se ingresa se una actual o pasada, no futura
    /*@PastOrPresent(message = "The date of creation can be past or present")*/
    private String creationDate;

    @Min(1)
    @Max(5)
    private int rating;
    @NotNull(message = "Genre id is required")
    private Long genreId;
    private List<CharacterDTO> movieCharacters;








}
