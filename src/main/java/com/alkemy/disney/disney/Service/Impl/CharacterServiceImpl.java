package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.CharacterMapper;
import com.alkemy.disney.disney.Repository.CharacterRepository;
import com.alkemy.disney.disney.Service.CharacterService;
import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CharacterServiceImpl implements CharacterService{
   @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapper characterMapper;

    //Post

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity= characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved=characterRepository.save(entity);
        CharacterDTO result=characterMapper.characterEntity2DTO(entitySaved);

        return result;
    }

    @Override
    public CharacterDTO update(Long id, CharacterDTO character) {
        return null;
    }

    //Get--List
    public List<CharacterDTO> getAllCharacters(){
    List<CharacterEntity>entities=characterRepository.findAll();
    List<CharacterDTO>result=characterMapper.characterEntityList2characterDtoList(entities);
    return result;
    }

    @Override
    public void addMovie(Long id, Long idCharacter) {

    }

    //GET DE CharacterBasicDTO
    @Override
    public List<CharacterBasicDTO> getCharacterBasicList() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterBasicDTO> result = characterMapper.charBasicEntityList2charBasicDtoList(entities);
        return result;
    }
}
