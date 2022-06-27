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
        dto.setId(entity.getId());////Ahora que la entidad persistida tiene un id, se lo seteamos al DTO
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());

        return dto;

    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities) {

        //Creamos la Lista donde guardaremos los DTOs
        List<GenreDTO> dtos = new ArrayList();

        /*For Each para recorrer la lista del parámetro y guardar cada Entity
        como un DTO usando el método de la propia clase para parsear de Entity 2 DTO */
        for (GenreEntity entity : entities) {
            dtos.add(genreEntity2DTO(entity));
        }
        //Devolvemos el listado ya completo
        return dtos;


    }
}