package com.alkemy.disney.disney.Mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {


    //DTO TO ENTITY
    public GenreEntity genreDTO2Entity(GenreDTO dto) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setName(dto.getName());
        genreEntity.setImage(dto.getImage());
        return genreEntity;

    }

    // ENTITY TO DTO
    public GenreDTO genreEntity2DTO(GenreEntity entity) {
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());

        return dto;

    }


    }