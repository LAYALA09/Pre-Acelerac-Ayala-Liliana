package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.CharacterMapper;
import com.alkemy.disney.disney.Repository.CharacterRepository;
import com.alkemy.disney.disney.Repository.specifications.CharacterSpecification;
import com.alkemy.disney.disney.Service.CharacterService;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.exception.InvalidDTOException;
import com.alkemy.disney.disney.exception.ParamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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


    // Setter/Field Injection of Dependencies so we can handle BeanCurrentlyInCreationException
    @Autowired
    public void setCharacterRepository(CharacterRepository characterRepository, CharacterSpecification characterSpecification, CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.characterSpecification = characterSpecification;
        this.characterMapper = characterMapper;
    }


    //GET FOR ID
    public CharacterDTO getCharDetails(Long id) {
        CharacterEntity dbChar = this.handleFindById(id);
        CharacterDTO resultDTO = characterMapper.entity2DTO(dbChar, true);

        return resultDTO;
    }


    //POST
    public CharacterDTO saveNewCharacter(CharacterDTO dto) {
        //Verifies if the DTO has all the attributes well setted
        validation(dto);
        CharacterEntity entity = characterMapper.charDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.entity2DTO(entitySaved, false);

        return result;
    }


    // DELETE
    public void deleteCharacterById(Long id) {
        if (!characterRepository.findById(id).isPresent())
            throw new ParamNotFound("The id does not correspond to any Character in the Database.");
        characterRepository.deleteById(id);
    }


    // PUT
    public CharacterDTO update(Long id, CharacterDTO dto) throws ParamNotFound {
        //Validation of new attributes
        validation(dto);
        Optional<CharacterEntity> result = characterRepository.findById(id);
        if (result.isPresent()) {
            CharacterEntity entity = characterMapper.updateCharacterDTO2Entity(result.get(), dto);
            CharacterEntity entityUpdated = characterRepository.save(entity);
            CharacterDTO dtoUpdated = characterMapper.entity2DTO(entityUpdated, false);
            return dtoUpdated;
        } else {
            throw new ParamNotFound("Requested character was not found.");
        }
    }


    //FILTERS
    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, movies);
        List<CharacterEntity> entityList = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> resultDTO = characterMapper.charListEntity2DTOList(entityList, true);
        return resultDTO;
    }


    // ERROR HANDLING
    //Method to check if the character exists
    public CharacterEntity handleFindById(Long id) {
        Optional<CharacterEntity> toBeFound = characterRepository.findById(id);
        if (!toBeFound.isPresent()) {
            throw new ParamNotFound("No Character for id: " + id);
        }
        return toBeFound.get();
    }

    public List<CharacterEntity> lookCreate(List<CharacterDTO> dtos) {

        /* Verifies id of each DTO. If it has a value, verifies if it exists in the DataBase.
         * If it does, it's added to the List. Else, it will be created and added using the Mapper. */
        List<CharacterEntity> entities = new ArrayList();
        for (CharacterDTO dto : dtos) {
            if (dto.getId() != null) {
                Optional<CharacterEntity> result = characterRepository.findById(dto.getId());
                if (result.isPresent()) {
                    entities.add(result.get());
                } else {
                    entities.add(characterMapper.charDTO2Entity(dto));
                }
            } else {
                entities.add(characterMapper.charDTO2Entity(dto));
            }
        }

        return entities;
    }


    //VALIDATION
    private void validation(CharacterDTO dto) {

        if (dto == null)
            throw new InvalidDTOException("Character cannot be null");
        if (dto.getName() == null || dto.getName().isEmpty())
            throw new InvalidDTOException("Character name cannot be empty or null");
        if (dto.getImage() == null || dto.getImage().isEmpty())
            throw new InvalidDTOException("Character image cannot be empty or null");
        if (dto.getHistory() == null || dto.getHistory().isEmpty())
            throw new InvalidDTOException("Character story cannot be empty or null");
        if (dto.getAge() == null)
            throw new InvalidDTOException("Character age cannot be null");
        if (dto.getWeight() == 0)
            throw new InvalidDTOException("Character weight cannot be null");
    }
}
