package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.MovieMapper;
import com.alkemy.disney.disney.Repository.MovieRepository;
import com.alkemy.disney.disney.Service.MovieService;
import com.alkemy.disney.disney.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper moviemapper;
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieDTO save(MovieDTO dto) {
        return null;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return null;
    }
}
