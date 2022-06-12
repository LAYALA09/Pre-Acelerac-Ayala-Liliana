package com.alkemy.disney.disney.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import java.time.LocalDate;


@Getter
@Setter
public class MovieBasicDTO {


    private String image;
    private String title;
    @Column(name= "date_creation")
    @DateTimeFormat(pattern = "YYYY/MM/dd")
    private LocalDate creationDate;

}
