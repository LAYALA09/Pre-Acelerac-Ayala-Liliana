package com.alkemy.disney.disney.Mapper;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {
    //dto to entity
    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImageUrl(dto.getImageUrl());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setHistory(dto.getHistory());
        return characterEntity;

    }

    //entity to dto
    public CharacterDTO characterEntity2DTO(CharacterEntity entity) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImageUrl(entity.getImageUrl());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        return dto;
    }

    //List--get

    public List<CharacterDTO>characterEntityList2characterDtoList(List<CharacterEntity> entities) {
      List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(this.characterEntity2DTO(entity));
        }
        return dtos;
    }
    // --- Entity -> BasicDTO ---
    private CharacterBasicDTO charBasicEntity2DTOBasic(CharacterEntity entity) {
        CharacterBasicDTO dto = new CharacterBasicDTO();
        dto.setImageUrl(entity.getImageUrl());
        dto.setName(entity.getName());
        return dto;
    }
    // --- BasicList<Entity> -> BasicList<DTO> ---
    public List<CharacterBasicDTO>charBasicEntityList2charBasicDtoList(List<CharacterEntity> entities) {
        List<CharacterBasicDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(this.charBasicEntity2DTOBasic(entity));
        }
        return dtos;
    }

}

