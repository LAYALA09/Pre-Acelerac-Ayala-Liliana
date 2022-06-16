package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.GenreMapper;
import com.alkemy.disney.disney.Repository.GenreRepository;
import com.alkemy.disney.disney.Service.GenreService;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private GenreRepository genreRepository;


    //Post
    public GenreDTO save(GenreDTO dto) {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity entitySaved = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);
        return result;
    }





}
