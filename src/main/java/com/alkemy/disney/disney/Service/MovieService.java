package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    //Post
    MovieDTO save(MovieDTO dto);
    //Get
    List<MovieDTO> getAllMovies();
    List<MovieBasicDTO> getMovieBasicList();

    // Delete
    void deleteMovieById(Long id);
}
