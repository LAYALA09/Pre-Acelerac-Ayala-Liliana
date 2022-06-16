package com.alkemy.disney.disney.Controller;

import com.alkemy.disney.disney.Service.GenreService;
import com.alkemy.disney.disney.dto.GenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
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


    //3. Creación de Géneros
    //Post Genre
    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genre) {

        GenreDTO genreUpdated = genreService.save(genre);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body( genreUpdated);

    }



}
