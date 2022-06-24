package com.alkemy.disney.disney.Controller;

import com.alkemy.disney.disney.Service.MovieService;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    //6. Detalle de Película con sus personajes
    //GET FOR ID
    @GetMapping("/{i}")
    public ResponseEntity<MovieDTO> getDetailsById(@Valid @PathVariable Long id) {
        MovieDTO movie = movieService.getMovieDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    // 8. Listado de Películas== imagen, título y fecha de creación
    //GET MOVIEBASIC
    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getBasicAll() {
        List<MovieBasicDTO> moviesBasic = movieService.getBasicMoviesList();
        return ResponseEntity.ok().body(moviesBasic);
    }

    //7. Creación, Edición y Eliminación de Película
    //POST
    @PostMapping
    public ResponseEntity<MovieDTO> postNewMovie(@Valid @RequestBody MovieDTO newMovie){
        MovieDTO createdMovie = movieService.saveNewMovie(newMovie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }


    @PostMapping("/{movieId}/character/{charId}")
    public ResponseEntity<Void> addChar(@Valid @PathVariable Long movieId, @PathVariable Long charId) {
        movieService.addCharacterToMovie(movieId, charId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> editMovie(@Valid @PathVariable Long id, @RequestBody MovieDTO movieToEdit) {
        MovieDTO editedMovie = movieService.editMovieById(id, movieToEdit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedMovie);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


    //8.Búsqueda de Películas
    /**
     * Returns a List of Movies as DTO that meet the specifications received
     * @param title Filter by title
     * @param genreId Filter by genre
     * @param order Order by CreationDate (only accepting ASC / DESC)
     * @return a List of Movies as DTO that meet the specifications received
     */
    //Filter
    @GetMapping()
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieDTO> filteredMovies = movieService.getByFilters(title, genreId, order);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(filteredMovies);


    }




}
