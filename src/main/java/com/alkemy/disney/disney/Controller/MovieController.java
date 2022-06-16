package com.alkemy.disney.disney.Controller;

import com.alkemy.disney.disney.Service.MovieService;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(name = "movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

   //6. Detalle de Película con sus personajes
    //Get Movies
    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity
                .ok()
                .body(movies);
    }

    // 8. Listado de Películas== imagen, título y fecha de creación
    //Get Movie Basic
    @GetMapping("/all")
    public ResponseEntity<List<MovieBasicDTO>> getBasicAll() {
        List<MovieBasicDTO> moviesBasic = movieService.getMovieBasicList();
        return ResponseEntity.ok().body(moviesBasic);
    }

    //7. Creación, Edición y Eliminación de Película
    //Post
    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
        MovieDTO movieUpdate = movieService.save(movie);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieUpdate);

    }
  //Post whit character
    @PostMapping("/{movieId}/character/{charId}")
    public ResponseEntity<Void> addChar(@PathVariable Long movieId, @PathVariable Long charId){
        movieService.addCharacterToMovie(movieId, charId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
    //Post with genre
    @PostMapping("/{movieId}/genre/{genreId}")
    public ResponseEntity<Void> addGenre(@PathVariable Long movieId, @PathVariable Long genreId){
        movieService.addGenreToMovie(movieId, genreId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
    //Put
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> editMovie(@PathVariable Long id, @RequestBody MovieDTO movieToEdit){
        MovieDTO editedMovie = movieService.editMovieById(id, movieToEdit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedMovie);
    }
    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


    //8.Búsqueda de Películas
    //Filter
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Long> genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieDTO> filteredMovies = movieService.getByFilters(title, genre, order);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(filteredMovies);


    }

    //9. Agregar/Remover personajes a una película
    //TODO: FALTA HACER ESTE PUNTO


}
