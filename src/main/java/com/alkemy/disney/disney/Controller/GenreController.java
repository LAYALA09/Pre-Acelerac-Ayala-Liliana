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
    //Delete Genre
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        //Le pasamos el parámetro recibido desde el path al método de eliminación del Service
        genreService.delete(id);

        //Devolvemos el código 201 de creado
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //EndPoint para actualizar los atributos del registro correspondiente al id pasado como Path Variable
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO dto) throws ChangeSetPersister.NotFoundException {

        //Llamamos al método del Service pasandole el id del registro a actualizar y el dto con todos los atributos nuevos
        GenreDTO result = genreService.update(id, dto);

        //Devolvemos un ResponseEntity con el dto actualizado en el body
        return ResponseEntity.ok().body(result);
    }
}
