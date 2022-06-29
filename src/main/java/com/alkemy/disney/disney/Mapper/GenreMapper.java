package com.alkemy.disney.disney.Mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        //Ahora que la entidad persistida tiene un id, se lo seteamos al DTO
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());

        return dto;

    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities) {
        //Creamos la Lista donde guardaremos los DTOs
        List<GenreDTO> dtos = new ArrayList();
        for (GenreEntity entity : entities) {
            dtos.add(genreEntity2DTO(entity));
        }

        return dtos;


    }
}