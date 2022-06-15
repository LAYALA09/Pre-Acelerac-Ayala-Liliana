package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import java.util.List;
import java.util.Set;

public interface MovieService {

    //Post
    MovieDTO save(MovieDTO dto);

    //Get
    List<MovieDTO> getAllMovies();
    List<MovieBasicDTO> getMovieBasicList();

    // Delete
    void deleteMovieById(Long id);

    // FILTERS
    List<MovieDTO> getByFilters(String name, Set<Long> genre, String order);
}
