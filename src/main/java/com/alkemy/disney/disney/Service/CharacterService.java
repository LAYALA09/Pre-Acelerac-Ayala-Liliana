package com.alkemy.disney.disney.Service;


import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import java.util.List;
import java.util.Set;

public interface CharacterService {

    // GET FOR ID
    CharacterDTO getCharDetails(Long id);

    List<CharacterEntity> lookCreate(List<CharacterDTO> dtos);

    // POST
    CharacterDTO saveNewCharacter(CharacterDTO newChar);//ok postman

    // DEL FOR ID
    void deleteCharacterById(Long id);//ok postman

    // PUT
    CharacterDTO update(Long id, CharacterDTO dto) throws ParamNotFound;

    // FILTERS OF NAME, AGE, MOVIES, LIST CHARACTER
    List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies);








}
