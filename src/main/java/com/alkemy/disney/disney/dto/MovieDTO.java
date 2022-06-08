package com.alkemy.disney.disney.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class MovieDTO {
    private Long id;
    @NotBlank(message = "Image is required")
    private String imageUrl;


    @NotBlank(message = "Title is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "Title con-tains invalid characters")
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Creation date is required")
    @PastOrPresent(message = "The date of creation can be past or present")
    private Date creationDate;

    @NotBlank(message = "The grade is required")
    @Pattern(regexp = "[1,2,3,4,5]", message = "Grade contains invalid charac-ters")
    private Integer rating;

    private List<CharacterDTO> characters;
    //saque lista de generos






}
