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
    @NotBlank(message = "Image is required")
    private String image;
    @NotBlank(message = "Title is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "Title contains invalid characters")
    private String title;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "Creation date is required")
    //@PastOrPresent es para validar que la fecha que se ingresa se una actual o pasada, no futura
    /*@PastOrPresent(message = "The date of creation can be past or present")*/
    private String creationDate;
    @NotNull(message = "The rating is required")
   /*@Pattern(regexp = "[1,2,3,4,5]", message = "rating contains invalid charac-ters")*/
    @Min(1)
    @Max(5)
    private int rating;
    @NotNull(message = "Genre id is required")
    private Long genreId;
    private List<CharacterDTO> movieCharacters;








}
