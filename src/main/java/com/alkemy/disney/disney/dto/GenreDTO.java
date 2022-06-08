package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter

public class GenreDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Image is required")
    private String imageUrl;

//saque lista de peliculas


}
