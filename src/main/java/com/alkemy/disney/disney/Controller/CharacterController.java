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
import java.util.Set;

@RestController
@RequestMapping("characters")
//Controller==> se recibe una solicitud y se devuelve una respuesta
public class CharacterController {
    @Autowired
    private CharacterService characterService;
   //5. Detalle de Personaje
   //Get Character
    @GetMapping("/all")
    public ResponseEntity<List<CharacterDTO>> getAll() {
        List<CharacterDTO> characters = characterService.getAllCharacters();
        return ResponseEntity
                .ok()
                .body(characters);
    }

   //== GET ==Listado de personaje punto 3: image and name //CharacterBasicDTO
    @GetMapping("/all")
    public ResponseEntity<List<CharacterBasicDTO>> getBasicCharacters(){
        List<CharacterBasicDTO> character = characterService.getCharacterBasicList();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(character);
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id){
        CharacterDTO characterDetails = characterService.getCharacterDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(characterDetails);
    }

    //4. Creación, Edición y Eliminación de Personajes (CRUD)

   // Post
    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
        //save
        CharacterDTO characterUpdated = characterService.save(character);
        return ResponseEntity//201
                .status(HttpStatus.CREATED)
                .body(characterUpdated);
    }



    //Put
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO dto) throws ChangeSetPersister.NotFoundException {
        CharacterDTO result = characterService.update(id, dto);
        return ResponseEntity
                .ok()
                .body(result);
    }

    // == DELETE ==
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
       characterService.deleteCharacterById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
    //6. Búsqueda de Personajes
    //  Filters
    @GetMapping()
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> movies
    ){
        List<CharacterDTO> characterList = characterService.getByFilters(name, age, movies);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(characterList);
    }



}
