package com.alkemy.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;import javax.persistence.Column;
import java.time.LocalDate;


@Getter
@Setter
public class MovieBasicDTO {

    private String image;
    private String title;

    private String creationDate;

}
