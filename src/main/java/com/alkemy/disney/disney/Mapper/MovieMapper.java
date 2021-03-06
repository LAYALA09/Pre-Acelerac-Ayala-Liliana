package com.alkemy.disney.disney.Mapper;

import com.alkemy.disney.disney.Repository.CharacterRepository;
import com.alkemy.disney.disney.Service.CharacterService;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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
        entity.setMovieCharacters(characterService.lookCreate(dto.getMovieCharacters()
                )
        );

        return entity;
    }

    // Entity to DTO
    public MovieDTO MovieEntity2DTO(MovieEntity dbMovie, boolean b) {
        MovieDTO dto = new MovieDTO();

        dto.setId(dbMovie.getId());
        dto.setImage(dbMovie.getImage());
        dto.setTitle(dbMovie.getTitle());
        dto.setCreationDate(this.localDate2String(dbMovie.getCreationDate()));
        dto.setRating(dbMovie.getRating());
        dto.setGenreId(dbMovie.getGenreId());
        //I indicate that I save the character but not the movie
        if (b) {
            dto.setMovieCharacters(characterMapper.charListEntity2DTOList(dbMovie.getMovieCharacters(), false));

        }
        return dto;
    }


    //List<Entity> to List<DTO>
    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entList, boolean b) {
        List<MovieDTO> dtoList = new ArrayList<>();
        for (MovieEntity ent : entList) {
            dtoList.add(this.MovieEntity2DTO(ent, b));
        }

        return dtoList;
    }


    //DTO  A ENTITY
    public MovieEntity updateMovieDTO2Entity(MovieEntity entity, MovieDTO dto) {

        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(String2LocalDate(dto.getCreationDate()));
        entity.setRating(dto.getRating());
        entity.setGenreId(dto.getGenreId());
        entity.setMovieCharacters(
                characterService.lookCreate(
                        dto.getMovieCharacters()
                )
        );

        return entity;
    }


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
