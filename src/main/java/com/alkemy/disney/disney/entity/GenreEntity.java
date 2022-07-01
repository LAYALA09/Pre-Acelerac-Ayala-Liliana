package com.alkemy.disney.disney.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table (name = "genres")
@Entity
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id=?") // Indicamos que query ejecutar al llamar un DELETE
@Where(clause = "deleted=false") // Le agrega una clausula extra a las queries que se realicen para diferenciar a los borrados de los no borrados
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Image is required")
    private String image;
    private boolean deleted = Boolean.FALSE; //Boolean to run a SOFT DELETE




}
