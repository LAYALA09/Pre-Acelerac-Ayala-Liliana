package com.alkemy.disney.disney.Controller;
import com.alkemy.disney.disney.Service.CharacterService;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.exception.ParamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("characters")
public class CharacterController {

@Autowired
CharacterService characterService;

   //GET DETAILS BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id){
        CharacterDTO charDetails = characterService.getCharDetails(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(charDetails);
    }

    // PUT CHARACTER
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@Valid @PathVariable Long id, @RequestBody CharacterDTO dto) throws ParamNotFound {
        CharacterDTO result = characterService.update(id, dto);
        return ResponseEntity
                .ok()
                .body(result);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById( @PathVariable Long id){
        characterService.deleteCharacterById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    // FILTERS
    @GetMapping("")
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> movies
    ){
        List<CharacterDTO> charList = characterService.getByFilters(name, age, movies);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(charList);
    }

    //POST
    @PostMapping
    public ResponseEntity<CharacterDTO> saveNewCharacter(@Valid @RequestBody CharacterDTO character) {
        CharacterDTO characterUpdated = characterService.saveNewCharacter(character);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(characterUpdated);
    }
}
