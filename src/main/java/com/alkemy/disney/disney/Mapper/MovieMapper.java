package com.alkemy.disney.disney.Mapper;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {
    //dto to entity--post
    public MovieEntity movieDTO2Entity(MovieDTO dto) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setImageUrl(dto.getImageUrl());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setCreationDate(dto.getCreationDate());
        movieEntity.setRating(dto.getRating());
        return movieEntity;

    }

    //entity to dto--post
    public MovieDTO movieEntity2DTO(MovieEntity entity) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImageUrl(entity.getImageUrl());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
        dto.setRating(entity.getRating());
        return dto;
    }

    //Get
    public List<MovieDTO> movieEntityList2MovieDtoList(List<MovieEntity> entities) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtos.add(this.movieEntity2DTO(entity));
        }
        return dtos;
    }
}
