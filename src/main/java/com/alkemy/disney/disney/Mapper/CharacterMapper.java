package com.alkemy.disney.disney.Mapper;


import com.alkemy.disney.disney.Service.MovieService;
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
    private MovieService movieService;

    // DTO TO Entity
    public CharacterEntity charDTO2Entity(CharacterDTO newChar) {
        CharacterEntity newEntity = new CharacterEntity();
        newEntity.setImage(newChar.getImage());
        newEntity.setName(newChar.getName());
        newEntity.setAge(newChar.getAge());
        newEntity.setWeight(newChar.getWeight());
        newEntity.setHistory(newChar.getHistory());

        return newEntity;
    }

    // ENTITY TO DTO
    //uso boolean si debe cargar o no ese atributo
    public CharacterDTO entity2DTO(CharacterEntity savedEntity, Boolean fetchMovies) {
        CharacterDTO newDTO = new CharacterDTO();
        newDTO.setId(savedEntity.getId());
        newDTO.setImage(savedEntity.getImage());
        newDTO.setName(savedEntity.getName());
        newDTO.setAge(savedEntity.getAge());
        newDTO.setWeight(savedEntity.getWeight());
        newDTO.setHistory(savedEntity.getHistory());
        if (fetchMovies) {
            newDTO.setCharacterMovies(movieMapper.movieEntityList2DTOList(savedEntity.getCharacterMovies(), false));
        }
        return newDTO;
    }


    public CharacterEntity updateCharacterDTO2Entity(CharacterEntity entity, CharacterDTO dto) {

        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());

        return entity;
    }

    //List<Entity> TO List<DTO>

    public List<CharacterDTO> charListEntity2DTOList(List<CharacterEntity> movieCharacters, boolean b) {
        List<CharacterDTO> newList = new ArrayList<>();
        for (CharacterEntity ent : movieCharacters) {
            newList.add(this.entity2DTO(ent, b));
        }
        return newList;
    }




}

