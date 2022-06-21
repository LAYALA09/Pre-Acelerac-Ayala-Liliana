package com.alkemy.disney.disney.Controller;
import com.alkemy.disney.disney.Service.CharacterService;
import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
//Controller==> se recibe una solicitud y se devuelve una respuesta
public class

CharacterController {
    private CharacterService  characterService;

    // GET
    @GetMapping("/all")
    public ResponseEntity<List<CharacterBasicDTO>> getBasicCharacters(){
        List<CharacterBasicDTO> charDTO = characterService.getCharacterBasicList();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(charDTO);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id){
        CharacterDTO charDetails = characterService.getCharDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(charDetails);
    }

    // POST
    @PostMapping
    public ResponseEntity<CharacterDTO> postNewCharacter(@Valid @RequestBody CharacterDTO newChar){
        CharacterDTO createdChar = characterService.saveNewCharacter(newChar);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChar);
    }

    // PUT CHARACTER
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> editCharacter(@Valid @PathVariable Long id, @RequestBody CharacterDTO charToEdit){
        CharacterDTO editedChar = characterService.editCharacterById(id, charToEdit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedChar);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById( @PathVariable Long id){
        characterService.deleteCharacterById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // FILTERS
    @GetMapping()
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> movies
    ){
        List<CharacterDTO> charList = characterService.getByFilters(name, age, movies);
        return ResponseEntity.status(HttpStatus.OK).body(charList);
    }
}
