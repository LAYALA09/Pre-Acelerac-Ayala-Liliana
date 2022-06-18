package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.MovieMapper;
import com.alkemy.disney.disney.Repository.MovieRepository;
import com.alkemy.disney.disney.Repository.specifications.CharacterSpecification;
import com.alkemy.disney.disney.Repository.specifications.MovieSpecifications;
import com.alkemy.disney.disney.Service.MovieService;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper moviemapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreServiceImpl genreService;
    @Autowired
    private CharacterServiceImpl characterService;
    @Autowired
    private MovieSpecifications movieSpecifications;

    //Post
    @Override
    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = moviemapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = moviemapper.movieEntity2DTO(entitySaved,false);
        return result;
    }
    //get x id
    @Autowired
    public MovieDTO getMovieDetails(Long id) {
        MovieEntity dbMovie = this.handleFindById(id);
        MovieDTO resultDTO =moviemapper.movieEntity2DTO(dbMovie, true);
        return resultDTO;
    }
    @Override
    public void addCharacterToMovie(Long movieId, Long charId) {
        MovieEntity savedMovie = this.handleFindById(movieId);
        CharacterEntity savedChar = characterService.handleFindById(charId);
        savedMovie.getMovieCharacters().size();
        savedMovie.addCharacterToMovie(savedChar);
        movieRepo.save(savedMovie);
    }
    //Get
    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = moviemapper.movieEntityList2MovieDtoList(entities, true);
        return null;
    }

    // --- PUT ---
    @Override
    public MovieDTO editMovieById(Long id, MovieDTO movieToEdit) {
        MovieEntity savedMovie = this.handleFindById(id);
        savedMovie.setImage(movieToEdit.getImage());
        savedMovie.setTitle(movieToEdit.getTitle());
        savedMovie.setRating(movieToEdit.getRating());
        savedMovie.setCreationDate(moviemapper.String2LocalDate(movieToEdit.getCreationDate()));
        MovieEntity editedMovie = movieRepository.save(savedMovie);
        MovieDTO resultDTO = moviemapper.movieEntity2DTO(editedMovie, false);
        return resultDTO;
    }


    //--- Entity -> BasicDTO ---
    public MovieBasicDTO entity2BasicDTO(MovieEntity entity) {
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(this.localDate2String(entity.getCreationDate()));
        return dto;
    }



    //List<Entity to List<BasicDTO>
    public List<MovieBasicDTO> entityList2BasicDTO(List<MovieEntity> entities) {
        List<MovieBasicDTO> dtoList = new ArrayList<>();
        for(MovieEntity ent : entities) {
            dtoList.add(this.entity2BasicDTO(ent));
        }
        return dtoList;
    }

    //Delete
    @Override
    public void deleteMovieById(Long id) {
       movieRepository.deleteById(id);

    }

    // Filters
    @Override
    public List<MovieDTO> getByFilters(String title, Set<Long> genre, String order) {
        MovieFiltersDTO movieFilters = new MovieFiltersDTO(title, genre, order);
        List<MovieEntity> entityList = movieRepository.findAll(movieSpecifications.getFiltered(movieFilters));
        List<MovieDTO> resultDTO = moviemapper.movieEntityList2MovieDtoList(entityList, true);
        return resultDTO;
    }

    // -Error Handling
    public MovieEntity handleFindById(Long id) {
        Optional<MovieEntity> toBeFound = movieRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Movie for id: " + id);
        }
        return toBeFound.get();
    }



}
