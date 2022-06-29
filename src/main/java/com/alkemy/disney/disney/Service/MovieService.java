package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.MovieDTO;
import java.util.List;


public interface MovieService {

    // GET FOR ID
    MovieDTO getMovieDetails(Long id);

    // POST
    MovieDTO saveNewMovie(MovieDTO newMovie);
    void addCharacterToMovie(Long movieId, Long charId);

    // DEL FOR ID
    void deleteMovieById(Long id);

    // PUT
    MovieDTO editMovieById(Long id, MovieDTO movieToEdit);

    // FILTERS FOR TITLE, GENRE ID, ORDER, LIST MOVIE
     List<MovieDTO> getByFilters(String title, Long genreId, String order);




}
