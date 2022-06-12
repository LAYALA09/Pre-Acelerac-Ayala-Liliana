package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    //Post
    MovieDTO save(MovieDTO dto);
    //Get
    List<MovieDTO> getAllMovies();

    // Delete
    void deleteMovieById(Long id);
}
