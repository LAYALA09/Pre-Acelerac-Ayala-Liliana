package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.CharacterMapper;
import com.alkemy.disney.disney.Repository.CharacterRepository;
import com.alkemy.disney.disney.Repository.specifications.CharacterSpecification;
import com.alkemy.disney.disney.Service.CharacterService;
import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterSpecification characterSpecification;

    // --- GET ---
    @Override
    public List<CharacterBasicDTO> getCharacterBasicList() {
        List<CharacterEntity> myList = characterRepository.findAll();
        List<CharacterBasicDTO> resultDTO = characterMapper.basicListEntity2DTO(myList);
        return resultDTO;
    }

    @Override
    public CharacterDTO getCharDetails(Long id) {
        CharacterEntity dbChar = this.handleFindById(id);
        CharacterDTO resultDTO = characterMapper.entity2DTO(dbChar,true);
        return resultDTO;
    }

    // --- POST ---
    @Override
    public CharacterDTO saveNewCharacter(CharacterDTO newChar) {
        CharacterEntity newEntity = characterMapper.charDTO2Entity(newChar);
        CharacterEntity savedEntity = characterRepository.save(newEntity);
        CharacterDTO savedChar = characterMapper.entity2DTO(savedEntity, false);
        return savedChar;
    }

    // --- DELETE ---
    @Override
    public void deleteCharacterById(Long id) {
        characterRepository.deleteById(id);
    }

    // == PUT ==
    @Override
    public CharacterDTO editCharacterById(Long id, CharacterDTO charToEdit) {
        CharacterEntity savedChar = this.handleFindById(id);
        savedChar.setImage(charToEdit.getImage());
        savedChar.setName(charToEdit.getName());
        savedChar.setAge(charToEdit.getAge());
        savedChar.setWeight(charToEdit.getWeight());
        savedChar.setHistory(charToEdit.getHistory());
        CharacterEntity editedChar = characterRepository.save(savedChar);
        CharacterDTO resultDTO = characterMapper.entity2DTO(editedChar, false);
        return resultDTO;
    }

    // --- FILTERS ---
    @Override
    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, movies);
        List<CharacterEntity> entityList = characterRepository.findAll(characterSpecification.getFiltered(filtersDTO));
        List<CharacterDTO> resultDTO = characterMapper.charListEntity2DTOList(entityList, true);
        return resultDTO;
    }

    // --- ERROR HANDLING ---
    public CharacterEntity handleFindById(Long id) {
        Optional<CharacterEntity> toBeFound = characterRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Character for id: " + id);
        }
        return toBeFound.get();
    }
}
