package com.alkemy.disney.disney.Mapper;

import com.alkemy.disney.disney.Repository.CharacterRepository;
import com.alkemy.disney.disney.Service.CharacterService;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieMapper {
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterRepository characterRepository;



    //DTO to  Entity
    public MovieEntity movieDTO2Entity(MovieDTO dto) {
        MovieEntity entity = new MovieEntity();
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setRating(dto.getRating());
        entity.setCreationDate(this.String2LocalDate(dto.getCreationDate()));
        entity.setGenreId(dto.getGenreId());
        entity.setMovieCharacters(characterService.look4OrCreate(dto.getMovieCharacters()));

        return entity;
    }

    // Entity to DTO
    public MovieDTO entity2DTO(MovieEntity dbMovie, boolean b) {
        MovieDTO dto = new MovieDTO();
        dto.setId(dbMovie.getId());
        dto.setImage(dbMovie.getImage());
        dto.setTitle(dbMovie.getTitle());
        dto.setRating(dbMovie.getRating());
        dto.setGenreId(dbMovie.getGenreId());
        dto.setCreationDate(this.localDate2String(dbMovie.getCreationDate()));
        //indico que guarde el personaje pero no  la pelicula
        if(b) {
            dto.setMovieCharacters(characterMapper.charListEntity2DTOList(dbMovie.getMovieCharacters(),false));

        }
        return dto;
    }


    // === List<Entity> to List<DTO> ===
    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entList, boolean b){
        List<MovieDTO> dtoList = new ArrayList<>();
        for(MovieEntity ent : entList) {
            dtoList.add(this.entity2DTO(ent, b));
        }
        return dtoList;
    }


    //Entity to BasicDto
    public MovieBasicDTO entity2BasicDTO(MovieEntity ent) {
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setImage(ent.getImage());
        dto.setTitle(ent.getTitle());
        dto.setCreationDate(this.localDate2String(ent.getCreationDate()));
        return dto;
    }

    //List<Entity> to List<BasicDTO>
    public List<MovieBasicDTO> entityList2BasicDTO(List<MovieEntity> dbList) {
        List<MovieBasicDTO> newList = new ArrayList<>();
        for(MovieEntity ent : dbList) {
            newList.add(this.entity2BasicDTO(ent));
        }
        return newList;
    }

    //Utils
    public LocalDate String2LocalDate(String enteredDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate transformedDate = LocalDate.parse(enteredDate, formatter);
        return transformedDate;
    }

    public String localDate2String(LocalDate dbDate) {
        String formattedDate = dbDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return formattedDate;
    }
}
