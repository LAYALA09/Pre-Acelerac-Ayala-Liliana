package com.alkemy.disney.disney.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table (name = "genre")
@Entity
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id=?") // Indicamos que query ejecutar al llamar un DELETE
@Where(clause = "deleted=false") // Le agrega una clausula extra a las queries que se realicen para diferenciar a los borrados de los no borrados
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;
    private String name;
    private String image;

    private boolean deleted = Boolean.FALSE; // Booleano para ejecutar un SOFT DELETE




}
