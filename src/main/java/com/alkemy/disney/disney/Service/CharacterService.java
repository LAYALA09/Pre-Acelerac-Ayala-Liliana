package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import java.util.List;
import java.util.Set;

public interface CharacterService {

    //Post
    CharacterDTO save(CharacterDTO dto);

    //Put
    CharacterDTO update(Long id, CharacterDTO dto) throws ChangeSetPersister.NotFoundException;

    //Get All and Get id--Get CharacterBasic
    List<CharacterDTO> getAllCharacters();
    List<CharacterBasicDTO> getCharacterBasicList();
    CharacterDTO getCharacterDetails(Long id);

    // Delete
    void deleteCharacterById(Long id);
    // FILTERS
    List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies);





    //TODO: Falta updateCharacter

    // Filters
    List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies);





}
