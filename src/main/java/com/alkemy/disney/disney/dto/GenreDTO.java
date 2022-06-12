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
    //Atributos
    private Long id;
    private String name;
    private String image;




}
