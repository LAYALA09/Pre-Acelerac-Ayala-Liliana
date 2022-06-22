package com.alkemy.disney.disney.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Getter
@Setter
public class MovieBasicDTO {

    @NotBlank(message = "Image is required")
    private String image;
    @NotBlank(message = "Title is required")
    private String title;
    @JsonFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "Creation date is required")
    private String creationDate;

}
