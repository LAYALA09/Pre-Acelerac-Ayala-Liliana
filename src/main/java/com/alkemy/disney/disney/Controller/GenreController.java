package com.alkemy.disney.disney.Controller;

import com.alkemy.disney.disney.Service.GenreService;
import com.alkemy.disney.disney.dto.GenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {
    // Para q Spring me inicialice este servicio uso Autowired
    @Autowired
    private GenreService genreService;

    //Get Genres
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll() {
        // se llama al servicio y se le pide el listado de Genres
        List<GenreDTO> genres = genreService.getAllGenres();
        // se crea el response request
        return ResponseEntity
                .ok()
                .body(genres);
    }

    //Post Genre
    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genre) {

        GenreDTO genreUpdated = genreService.save(genre);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body( genreUpdated);

    }
    // == DELETE ==
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
       genreService.deleteGenreById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
