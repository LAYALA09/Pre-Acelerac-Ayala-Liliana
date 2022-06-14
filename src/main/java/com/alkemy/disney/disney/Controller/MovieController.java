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

   //8. Detalle de Película / Serie con sus personajes
    //Get Movies
    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity
                .ok()
                .body(movies);
    }

    // 7. Listado de Películas== imagen, título y fecha de creación
    //Get Movie Basic
    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getBasicAll() {
        List<MovieBasicDTO> moviesBasic = movieService.getMovieBasicList();
        return ResponseEntity.ok().body(moviesBasic);
    }

    //9. Creación, Edición y Eliminación de Película / Serie
    //Post
    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
        MovieDTO movieUpdate = movieService.save(movie);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieUpdate);

    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
    //TODO: Falta update de MOvie

    //10.Búsqueda de Películas o Series

    //Filter
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Long> genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<MovieDTO> filteredMovies = movieService.getByFilters(title, genre, order);
        return ResponseEntity.status(HttpStatus.OK).body(filteredMovies);
    }



}
