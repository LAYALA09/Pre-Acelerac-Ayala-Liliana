package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.GenreMapper;
import com.alkemy.disney.disney.Repository.GenreRepository;
import com.alkemy.disney.disney.Service.GenreService;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.exception.InvalidDTOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private GenreRepository genreRepository;


    public GenreDTO saveNewGenre(GenreDTO dto) {

        //Valido primero que el DTO sea válido
        validation(dto);

        //Utilizo el método de conversión del Mapper para pasarle el DTO recibido y obtener el Entity
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);

        //Persisto el DTO ahora hecho Entidad en la DB
        GenreEntity entitySaved = genreRepository.save(entity);

        //Convierto la Entidad ya persistida de vuelta en un DTO, ahora con id
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);

        //Devuelvo el DTO con su id en la base de datos
        return result;
    }

    public List<GenreDTO> getAllGenres() {

        //Trae todos los géneros como Entidades del Repository
        List<GenreEntity> genres = genreRepository.findAll();

        //Convierte esa lista en tipo DTO usando un método de nuestro Mapper
        List<GenreDTO> result = genreMapper.genreEntityList2DTOList(genres);

        //Devuelve la Lista con los Géneros en formato DTO
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


}
