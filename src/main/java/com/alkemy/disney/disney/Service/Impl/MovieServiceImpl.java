package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.MovieMapper;
import com.alkemy.disney.disney.Repository.MovieRepository;
import com.alkemy.disney.disney.Service.MovieService;
import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper moviemapper;
    @Autowired
    private MovieRepository movieRepository;

    //Post
    @Override
    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = moviemapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = moviemapper.movieEntity2DTO(entitySaved);
        return result;
    }

    //Get
    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = moviemapper.movieEntityList2MovieDtoList(entities);
        return null;
    }

    @Override
    public List<MovieBasicDTO> getMovieBasicList() {
        return null;
    }

    //Delete
    @Override
    public void deleteMovieById(Long id) {
       movieRepository.deleteById(id);

    }



}
