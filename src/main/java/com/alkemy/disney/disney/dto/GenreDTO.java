package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GenreDTO {

    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Image is required")
    private String image;




}
