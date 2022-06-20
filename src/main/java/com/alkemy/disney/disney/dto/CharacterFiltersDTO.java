package com.alkemy.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterFiltersDTO {

    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Age is required")
    private Integer age;
    private Set<Long> movies;
}


