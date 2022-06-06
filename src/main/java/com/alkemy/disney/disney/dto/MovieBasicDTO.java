package com.alkemy.disney.disney.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
public class MovieBasicDTO {
    @NotBlank(message = "Image is required")
    private String imageUrl;

    @NotBlank(message = "Title is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "Title con-tains invalid characters")
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Creation date is required")
    @PastOrPresent(message = "The date of creation can be past or present")
    private Date creationDate;

}
