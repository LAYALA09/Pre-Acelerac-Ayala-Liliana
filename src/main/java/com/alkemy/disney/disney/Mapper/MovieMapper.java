package com.alkemy.disney.disney.Mapper;

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

    //dto to entity--post
    public MovieEntity movieDTO2Entity(MovieDTO dto) {

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(dto.getId());
        movieEntity.setImage(dto.getImage());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setRating(dto.getRating());
        movieEntity.setCreationDate(this.String2LocalDate(dto.getCreationDate()));

        return movieEntity;

    }

    //entity to dto--post
    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean b) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setRating(entity.getRating());
        dto.setCreationDate(this.localDate2String(entity.getCreationDate()));

        if(b) {
            dto.setCharacters(characterMapper.characterEntityList2characterDtoList(dbMovie.getMovieCharacters(),false));
            dto.setGenres(genreMapper.genreEntity2DTO(dbMovie.getMovieGenres()));
        }
        return dto;
    }

    //Get
    public List<MovieDTO> movieEntityList2MovieDtoList(List<MovieEntity> entities, boolean b) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtos.add(this.movieEntity2DTO(entity,b));
        }
        return dtos;
    }

    //--> Utils <--
    public LocalDate String2LocalDate(String enteredDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate transformedDate = LocalDate.parse(enteredDate, formatter);
        return transformedDate;
    }

    public String localDate2String(LocalDate dbDate) {
        String formattedDate = dbDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
        return formattedDate;
    }
}
