package com.alkemy.disney.disney.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.util.List;

@Setter
@Getter
public class MovieDTO {

    private Long id;
    @NotBlank(message = "image is required")
    private String image;
    @NotBlank(message = "title is required")
    private String title;
    @JsonFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "creation date is required")
    //@PastOrPresent es para validar que la fecha que se ingresa se una actual o pasada, no futura
    @PastOrPresent(message = "the date of creation can be past or present")
    private String creationDate;
    @NotNull(message = "the rating is required")
    /*@Pattern(regexp = "[1,2,3,4,5]", message = "rating contains invalid charac-ters")*/
    private Integer rating;
    @NotNull(message = "genre id is required")
    private Long genreId;
    private List<CharacterDTO> movieCharacters;








}
