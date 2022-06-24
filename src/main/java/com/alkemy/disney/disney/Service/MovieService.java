package com.alkemy.disney.disney.Service;


import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface MovieService {

    // GET
    List<MovieBasicDTO> getBasicMoviesList();
    MovieDTO getMovieDetails(Long id);




    // POST
    MovieDTO saveNewMovie(MovieDTO newMovie);
    void addCharacterToMovie(Long movieId, Long charId);



    // DEL
    void deleteMovieById(Long id);

    // PUT
    MovieDTO editMovieById(Long id, MovieDTO movieToEdit);

    // FILTERS
    public List<MovieDTO> getByFilters(String title, Long idGenre, String order);


}
