package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter

public class GenderDTO {
    private Long id;

    @Column(nullable = false)
    private String name;


    @Column(name = "image_url", nullable = false)
    private String imageUrl;


}
