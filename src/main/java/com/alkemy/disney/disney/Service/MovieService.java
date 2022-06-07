package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    MovieDTO save(MovieDTO dto);

    List<MovieDTO> getAllMovies();
}
