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

    // --- GET ---
    @Override
    public List<MovieBasicDTO> getBasicMoviesList() {
        List<MovieEntity> dbList = movieRepository.findAll();
        List<MovieBasicDTO> resultDTO = moviemapper.entityList2BasicDTO(dbList);
        return resultDTO;
    }

    @Override
    public MovieDTO getMovieDetails(Long id) {
        MovieEntity dbMovie = this.handleFindById(id);
        MovieDTO resultDTO = moviemapper.entity2DTO(dbMovie, true);
        return resultDTO;
    }

    // --- POST ---
    @Override
    public MovieDTO saveNewMovie(MovieDTO newMovie) {
        MovieEntity newEntity = moviemapper.movieDTO2Entity(newMovie);
        MovieEntity savedEntity = movieRepository.save(newEntity);
        MovieDTO resultDTO = moviemapper.entity2DTO(savedEntity, false);
        return resultDTO;
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
        MovieDTO resultDTO = moviemapper.entity2DTO(editedMovie, false);
        return resultDTO;
    }

    // --- DELETE ---
    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    // --- FILTERS ---
    @Override
    public List<MovieDTO> getByFilters(String title, Set<Long> genre, String order) {
        MovieFiltersDTO movieFilters = new MovieFiltersDTO(title, genre, order);
        List<MovieEntity> entityList = movieRepository.findAll(movieSpecifications.getFiltered(movieFilters));
        List<MovieDTO> resultDTO = moviemapper.movieEntityList2DTOList(entityList, true);
        return resultDTO;
    }

    // --- ERROR HANDLING ---
    public MovieEntity handleFindById(Long id) {
        Optional<MovieEntity> toBeFound = movieRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Movie for id: " + id);
        }
        return toBeFound.get();
    }
}
