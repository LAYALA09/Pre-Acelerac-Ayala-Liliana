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

    //Post
    @Override
    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, false);

        return result;
    }


    //Get All
    @Override
    public List<CharacterDTO> getAllCharacters() {
        List<CharacterEntity> characters = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntityList2characterDtoList(characters, true);
        return result;
    }

    //Get de CharacterBasicDTO
    @Override
    public List<CharacterBasicDTO> getCharacterBasicList() {
        List<CharacterEntity> characters = characterRepository.findAll();
        List<CharacterBasicDTO> result = characterMapper.characterEntityList2charBasicDtoList(characters);
        return result;
    }

    @Override
    public CharacterDTO getCharacterDetails(Long id) {
        CharacterEntity dbChar = this(id);
        CharacterDTO resultDTO = characterMapper.characterEntity2DTO(dbChar, true);
        return resultDTO;

        // Delete
        @Override
        public void deleteCharacterById(Long id){
            characterRepository.deleteById(id);
        }

        @Autowired
        public List<CharacterDTO> getByFilters (String name, Integer age, Set < Long > movies){
            CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, movies);
            List<CharacterEntity> entityList = characterRepository.findAll(characterSpecification.getFiltered(filtersDTO));
            List<CharacterDTO> resultDTO = characterMapper.characterEntityList2characterDtoList(entityList, true);
            return resultDTO;
        }

        //Put
        @Override
        public CharacterDTO update (Long id, CharacterDTO dto){
            CharacterEntity entity = this.handleFindById(id);
            entity.setImage(dto.getImage());
            entity.setName(dto.getName());
            entity.setAge(dto.getAge());
            entity.setWeight(dto.getWeight());
            entity.setHistory(dto.getHistory());
            CharacterEntity editedChar = characterRepository.save(entity);
            CharacterDTO resultDTO = characterMapper.characterEntity2DTO(editedChar, false);
            return resultDTO;
        }
        // --- FILTERS ---
        @Autowired
        public List<CharacterDTO> getByFilters (String name, Integer age, Set < Long > movies){
            CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, movies);
            List<CharacterEntity> entityList = characterRepository.findAll(characterSpecification.getFiltered(filtersDTO));
            List<CharacterDTO> resultDTO = characterMapper.characterEntityList2characterDtoList(entityList, true);
            return resultDTO;
        }

        //ERROR HANDLING
        public CharacterEntity handleFindById (Long id){
            Optional<CharacterEntity> toBeFound = characterRepository.findById(id);
            if (!toBeFound.isPresent()) {
                throw new ParamNotFound("No Character for id: " + id);
            }
            return toBeFound.get();
        }
    }
}
