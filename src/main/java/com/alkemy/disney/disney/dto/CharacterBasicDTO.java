package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CharacterBasicDTO {

    private Long id;
    @NotNull(message = "Image is required")
    private String image;
    @NotNull(message = "Name is required")
    private String name;


}
