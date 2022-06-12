package com.alkemy.disney.disney.Mapper;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;

import com.alkemy.disney.disney.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {
    @Autowired
    private MovieMapper movieMapper;

    // === DTO -> Entity ===
    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setHistory(dto.getHistory());
        return characterEntity;

    }

    //entity to dto
    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean fetchMovies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());

        return dto;


    }

     // --- List<Entity> -> List<DTO> ---
    public List<CharacterDTO> characterEntityList2characterDtoList(List<CharacterEntity> entities, boolean b) {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(this.characterEntity2DTO(entity,b));
        }
        return dtos;
    }

    // --- Entity -> BasicDTO ---
    private CharacterBasicDTO characterEntity2BasicDTO(CharacterEntity entity) {
        CharacterBasicDTO dto = new CharacterBasicDTO();
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        return dto;
    }

    // --- BasicList<Entity> -> BasicList<DTO> ---
    public List<CharacterBasicDTO> characterEntityList2charBasicDtoList(List<CharacterEntity> entities) {
        List<CharacterBasicDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(this.characterEntity2BasicDTO(entity));
        }
        return dtos;
    }


}

