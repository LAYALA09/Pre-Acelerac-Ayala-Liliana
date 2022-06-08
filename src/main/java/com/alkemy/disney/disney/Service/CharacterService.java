package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.GenreDTO;

import java.util.List;

public interface CharacterService {
    CharacterDTO save(CharacterDTO dto);//post
    CharacterDTO update(Long id,CharacterDTO character);//put
    List<CharacterDTO> getAllCharacters();//get
    List<CharacterBasicDTO> getCharacterBasicList();//get Character Básico
    void addMovie(Long id, Long idCharacter);//delete
}
