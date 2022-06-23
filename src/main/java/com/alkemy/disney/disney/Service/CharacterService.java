package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    // GET
    List<CharacterBasicDTO> getCharacterBasicList();
    CharacterDTO getCharDetails(Long id);

    List<CharacterEntity> look4OrCreate(List<CharacterDTO> dtos);

    // POST
    CharacterDTO saveNewCharacter(CharacterDTO newChar);

    // DEL
    void deleteCharacterById(Long id);

    // PUT
    CharacterDTO editCharacterById(Long id, CharacterDTO charToEdit);

    // FILTERS
    List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies);








}
