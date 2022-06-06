package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.CharacterMapper;
import com.alkemy.disney.disney.Repository.CharacterRepository;
import com.alkemy.disney.disney.Service.CharacterService;
import com.alkemy.disney.disney.dto.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CharacterServiceImpl implements CharacterService{
   @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapper characterMapper;


    public CharacterDTO save(CharacterDTO dto) {
        return null;
    }


    public List<CharacterDTO> getAllCharacters() {
        return null;
    }
}
