package com.alkemy.disney.disney.Mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

        public GenreEntity genreDTO2Entity (GenreDTO dto){
            GenreEntity genreEntity = new GenreEntity();
            genreEntity.setName(dto.getName());
            genreEntity.setImageUrl(dto.getImageUrl());
            return genreEntity;

    }
    public GenreDTO genreEntity2DTO (GenreEntity entity){
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImageUrl(entity.getImageUrl());
        return dto;

    }
    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity>entities){
            List<GenreDTO>dtos=new ArrayList<>();
            for (GenreEntity entity : entities){
                dtos.add(this.genreEntity2DTO(entity));

            }
            return dtos;
    }
}