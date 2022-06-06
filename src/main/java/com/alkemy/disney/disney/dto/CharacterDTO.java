package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

@Getter
@Setter
public class CharacterDTO {
    private Long id;
    @NotBlank(message = "Image is required")
    private String imageUrl;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Age is required")
    private Integer age;
    @NotNull(message = "Peso is required")
    private Float weight;
    @NotBlank(message = "History is required")
    private String history;
    private List<MovieDTO> Movies;


}
