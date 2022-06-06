package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.GenreDTO;

import java.util.List;

public interface CharacterService {
    CharacterDTO save(CharacterDTO dto);
    List<CharacterDTO> getAllCharacters();
}
