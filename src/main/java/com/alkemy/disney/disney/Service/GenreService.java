package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.GenreDTO;
import java.util.List;

public interface GenreService {

    //Post
    GenreDTO save(GenreDTO dto);

   //Get
    List<GenreDTO> getAllGenres();

    // Delete
    void deleteGenreById(Long id);
}
