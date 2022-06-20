package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.GenreMapper;
import com.alkemy.disney.disney.Repository.GenreRepository;
import com.alkemy.disney.disney.Service.GenreService;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.exception.InvalidDTOException;
import com.alkemy.disney.disney.exception.ParamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private GenreRepository genreRepository;


    public GenreDTO saveNewGenre(GenreDTO dto) {

        //Validamos primero que el DTO sea válido
        validation(dto);

        //Utilizamos el método de conversión del Mapper para pasarle el DTO recibido y obtener el Entity
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);

        //Persistimos el DTO ahora hecho Entidad en la DB
        GenreEntity entitySaved = genreRepository.save(entity);

        //Convertimos la Entidad ya persistida de vuelta en un DTO, ahora con id
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);

        //Devolvemos el DTO con su id en la base de datos
        return result;
    }
    private void validation(GenreDTO dto) {
        if (dto == null)
            throw new InvalidDTOException("Genre cannot be null");
        if (dto.getName() == null || dto.getName().isEmpty())
            throw new InvalidDTOException("Genre name cannot be empty or null");
        if (dto.getImage() == null || dto.getImage().isEmpty())
            throw new InvalidDTOException("Genre image cannot be empty or null");
    }

    // - Error Handling -
    public GenreEntity handleFindById(Long id) {
        Optional<GenreEntity> toBeFound = genreRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Genre for id: " + id);
        }
        return toBeFound.get();
    }






}
