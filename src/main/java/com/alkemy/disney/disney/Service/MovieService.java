package com.alkemy.disney.disney.Service;


import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import java.util.List;
import java.util.Set;

public interface MovieService {

    // GET
    List<MovieBasicDTO> getBasicMoviesList();
    MovieDTO getMovieDetails(Long id);
    void addCharacterToMovie(Long movieId, Long charId);
    // TODO: pendiente
   /* List<MovieDTO> getAllMovies();

    // TODO: pendiente
    /*void removeCharacterFromMovie(Long movieId, Long charId);No llegue a resolverlo*/

    // POST
    MovieDTO saveNewMovie(MovieDTO newMovie);

    // DEL
    void deleteMovieById(Long id);

    // PUT
    MovieDTO editMovieById(Long id, MovieDTO movieToEdit);

    // FILTERS
    List<MovieDTO> getByFilters(String name, Set<Long> genre, String order);


}
