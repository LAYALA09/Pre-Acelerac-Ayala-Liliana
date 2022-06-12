package com.alkemy.disney.disney.Controller;

import com.alkemy.disney.disney.Service.CharacterService;
import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

   //Get
    @GetMapping("/all")
    public ResponseEntity<List<CharacterDTO>> getAll() {
        List<CharacterDTO> characters = characterService.getAllCharacters();
        return ResponseEntity
                .ok()
                .body(characters);
    }

   //== GET ==Listado de personaje : image and name //CharacterBasicDTO
    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getBasicCharacters(){
        List<CharacterBasicDTO> character = characterService.getCharacterBasicList();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(character);
    }

   // Post
    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
        CharacterDTO characterUpdated = characterService.save(character);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(characterUpdated);
    }



    //Put
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO dto) throws ChangeSetPersister.NotFoundException {
        CharacterDTO result = characterService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    // == DELETE ==
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
       characterService.deleteCharacterById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
