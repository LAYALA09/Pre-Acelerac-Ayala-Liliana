package com.alkemy.disney.disney.Controller;

import com.alkemy.disney.disney.Service.MovieService;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

//Get Movies
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity
                .ok()
                .body(movies);
    }

//Post Movie
    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
        MovieDTO movieUpdate = movieService.save(movie);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieUpdate );

    }

//Get Movie Basic
    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getBasicAll() {
        List<MovieBasicDTO> moviesBasic = movieService.getBasicDTOList();
        return ResponseEntity.ok().body(moviesBasic);
    }
    // == DELETE ==
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        movieService.deletemovieById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Put Movie
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO dto) throws NotFoundException {
        MovieDTO result = movieService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

}
