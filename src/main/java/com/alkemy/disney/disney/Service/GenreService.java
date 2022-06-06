package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    GenreDTO save(GenreDTO dto);
    List<GenreDTO> getAllGenders();
}
