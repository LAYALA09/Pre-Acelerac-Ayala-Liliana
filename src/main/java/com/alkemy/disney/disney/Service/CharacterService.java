package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import java.util.List;
import java.util.Set;

public interface CharacterService {

    // GET

    CharacterDTO getCharDetails(Long id);

    List<CharacterEntity> lookCreate(List<CharacterDTO> dtos);

    // POST
    CharacterDTO saveNewCharacter(CharacterDTO newChar);

    // DEL
    void deleteCharacterById(Long id);

    // PUT
    CharacterDTO update(Long id, CharacterDTO dto) throws ParamNotFound;

    // FILTERS
    List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies);








}
