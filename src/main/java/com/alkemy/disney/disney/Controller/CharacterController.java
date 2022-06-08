package com.alkemy.disney.disney.Controller;

import com.alkemy.disney.disney.Service.CharacterService;
import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;
   //Get
    @GetMapping
    ResponseEntity<List<CharacterDTO>> getAll() {
        List<CharacterDTO> characters = characterService.getAllCharacters();
        return ResponseEntity
                .ok()
                .body(characters);
    }

   //== GET ==Listado de personaje : image and name //CharacterBasicDTO
    @GetMapping("/all")
    public ResponseEntity<List<CharacterBasicDTO>> getBasicCharacters(){
        List<CharacterBasicDTO> character = characterService.getCharacterBasicList();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(character);
    }
   // Post
    @PostMapping
    ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
        CharacterDTO characterguardado = characterService.save(character);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(characterguardado);
    }

    //Put
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO>update(@PathVariable Long id, @PathVariable CharacterDTO character){
        CharacterDTO result=this.characterService.update(id, character);
        return ResponseEntity
                .ok()
                .body(result);

    }

    @PostMapping("/{id} /movie/{idMovie}")
    public ResponseEntity<Void>addMovie(@PathVariable long id, @PathVariable Long idMovie){
        characterService.addMovie(id, idMovie);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();


    }



}
