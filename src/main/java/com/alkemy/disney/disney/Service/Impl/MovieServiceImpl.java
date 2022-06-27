package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.MovieMapper;
import com.alkemy.disney.disney.Repository.MovieRepository;
import com.alkemy.disney.disney.Repository.specifications.MovieSpecifications;
import com.alkemy.disney.disney.Service.CharacterService;
import com.alkemy.disney.disney.Service.MovieService;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.exception.InvalidDTOException;
import com.alkemy.disney.disney.exception.ParamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieSpecifications movieSpecifications;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterServiceImpl characterServiceImpl;

    // Setter/Field Injection of Dependencies so we can handle BeanCurrentlyInCreationException
    @Autowired
    public void setMovieRepository(MovieRepository movieRepository, MovieSpecifications movieSpecifications, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieSpecifications = movieSpecifications;
        this.movieMapper = movieMapper;

    }

    // GET MOVIE BASIC DTO
   /*public List<MovieBasicDTO> getBasicMoviesList() {
        List<MovieEntity> dbList = movieRepository.findAll();
        List<MovieBasicDTO> resultDTO = movieMapper.entityList2BasicDTO(dbList);
        return resultDTO;
    }*/


    //GET FOR ID
    public MovieDTO getMovieDetails(Long id) {
        MovieEntity dbMovie = this.handleFindById(id);
        MovieDTO resultDTO = movieMapper.MovieEntity2DTO(dbMovie, true);
        return resultDTO;
    }


    // POST
    public MovieDTO saveNewMovie(MovieDTO dto) {
        validation(dto);
        MovieEntity newEntity = movieMapper.movieDTO2Entity(dto);
        MovieEntity save = movieRepository.save(newEntity);
        MovieDTO resultDTO = movieMapper.MovieEntity2DTO(save, true);
        return resultDTO;
    }


    public void addCharacterToMovie(Long movieId, Long charId) {
        MovieEntity savedMovie = this.handleFindById(movieId);
        CharacterEntity savedChar = characterServiceImpl.handleFindById(charId);
        savedMovie.addCharacterToMovie(savedChar);
        movieRepository.save(savedMovie);
    }

    // PUT

    /**
     * Update
     * an Entity related to the received id with the new attributes from the received dto
     * @param id  of the entity to be updated
     * @param dto with all the new attributes
     * @return The Entity as DTO with its updated attributes
     * @throws ParamNotFound
     */
    public MovieDTO editMovieById(Long id, MovieDTO dto) throws ParamNotFound {
        //Validation of new attributes
        validation(dto);

        Optional<MovieEntity> result = movieRepository.findById(id);
        if (result.isPresent()) {
            MovieEntity entity = movieMapper.updateMovieDTO2Entity(result.get(), dto);
            MovieEntity entityUpdated = movieRepository.save(entity);
            MovieDTO dtoUpdated = movieMapper.MovieEntity2DTO(entityUpdated, true);
            return dtoUpdated;
        } else {
            throw new ParamNotFound("Requested movie was not found.");
        }
    }



    /**
     * Performs a logic delete to the Entity related to the received id
     * @param id of the entity to be deleted
     */
    //DELETE
    public void deleteMovieById(Long id) {
        if (movieRepository.findById(id) == null)
            throw new ParamNotFound("Movie requested was not found.");
        movieRepository.deleteById(id);
    }

    //FILTERS
    public List<MovieDTO> getByFilters(String title, Long genreId, String order) {

        /* Creates a filtersDTO with the specifications, brings all the entities which meet these and
         * then turns the list of entitites into a list of DTOs */
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title, genreId, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecifications.getByFilters(filtersDTO));
        List<MovieDTO> dtos = movieMapper.movieEntityList2DTOList(entities, true);
        return dtos;
    }


    // ERROR HANDLING
    //method para verificar si existe la película
    public MovieEntity handleFindById(Long id) {
        Optional<MovieEntity> toBeFound = movieRepository.findById(id);
        if (!toBeFound.isPresent()) {
            throw new ParamNotFound("No Movie for id: " + id);
        }
        return toBeFound.get();
    }

    //VALIDATION

    /**
     * Validates all attributes of the received dto and throws an exception if any does not meet the requirements
     *
     * @param dto to be validated
     */
    private void validation(MovieDTO dto) {
        if (dto == null)
            throw new InvalidDTOException("Movie cannot be null.");
        if (dto.getTitle() == null || dto.getTitle().isEmpty())
            throw new InvalidDTOException("Movie title cannot be empty or null");
        if (dto.getImage() == null || dto.getImage().isEmpty())
            throw new InvalidDTOException("Movie image cannot be empty or null");
        if (dto.getCreationDate() == null || dto.getCreationDate().isEmpty())
            throw new InvalidDTOException("Movie creation date cannot be empty or null");
        if (dto.getGenreId() == null)
            throw new InvalidDTOException("Movie genre id cannot be null");
        if (dto.getRating() == 0)
            throw new InvalidDTOException("Movie rating cannot be null");
        else if (dto.getRating() < 1 || dto.getRating() > 5)
            throw new InvalidDTOException("Movie rating cannot be less than 1 or greater than 5.");
        if (dto.getMovieCharacters() == null || dto.getMovieCharacters().isEmpty())
            throw new InvalidDTOException("Movie should have at least 1 character");
    }


}
