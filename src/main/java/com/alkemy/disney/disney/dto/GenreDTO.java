package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter

public class GenreDTO {
    private Long id;

    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    private List<MovieDTO> movies;


}
