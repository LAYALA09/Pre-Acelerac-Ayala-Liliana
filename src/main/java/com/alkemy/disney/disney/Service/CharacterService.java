package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CharacterService {
    //Post
    CharacterDTO save(CharacterDTO dto);

    //Put
    CharacterDTO update(Long id,CharacterDTO character)throws ChangeSetPersister.NotFoundException;

    //Get --Get CharacterBasic
    List<CharacterDTO> getAllCharacters();
    List<CharacterBasicDTO> getCharacterBasicList();


    // Delete
    void deleteCharacterById(Long id);





}
