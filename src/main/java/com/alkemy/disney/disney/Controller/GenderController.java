package com.alkemy.disney.disney.Controller;

import com.alkemy.disney.disney.Service.GenderService;
import com.alkemy.disney.disney.Service.Impl.GenderServiceImpl;
import com.alkemy.disney.disney.dto.GenderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("genders")
public class GenderController {
   // Para q Spring me inicialice este servicio uso Autowire
    @Autowired
    private GenderService genderService;
    @GetMapping
    public ResponseEntity<List<GenderDTO>>getAll() {
        List<GenderDTO> genders = genderService.getAllGenders();
        return ResponseEntity.ok().body(genders);
    }
    @PostMapping
    public ResponseEntity<GenderDTO> save(@RequestBody GenderDTO gender) {

        GenderDTO genderguardado= genderService.save(gender);
         return ResponseEntity.status(HttpStatus.CREATED).body(genderguardado);

    }
}
