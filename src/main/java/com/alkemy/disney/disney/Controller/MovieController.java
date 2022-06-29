package com.alkemy.disney.disney.Controller;

import com.alkemy.disney.disney.Service.MovieService;
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

    //GET FOR ID
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getDetailsById( @PathVariable Long id) {
        MovieDTO movie = movieService.getMovieDetails(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movie);
    }


    //POST
    @PostMapping
    public ResponseEntity<MovieDTO> postNewMovie(@Valid@RequestBody MovieDTO newMovie) {
        MovieDTO createdMovie = movieService.saveNewMovie(newMovie);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdMovie);
    }


    @PostMapping("/{movieId}/character/{charId}")
    public ResponseEntity<Void> addChar(@Valid @PathVariable Long movieId, @PathVariable Long charId) {
        movieService.addCharacterToMovie(movieId, charId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }


    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> editMovie(@Valid @PathVariable Long id, @RequestBody MovieDTO movieToEdit) {
        MovieDTO editedMovie = movieService.editMovieById(id, movieToEdit);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(editedMovie);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


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
