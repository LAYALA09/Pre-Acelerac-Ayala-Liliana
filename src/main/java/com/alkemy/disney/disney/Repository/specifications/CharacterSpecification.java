package com.alkemy.disney.disney.Repository.specifications;

import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getFiltered(CharacterFiltersDTO filtersDTO
    ){

        // LAMBDA Function:
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // == Name ==
            // IF hay algo en la String:
            // 	predicates.add(
            //		Buildeamos un SQL LIKE -> (Database Table, a Comparar) --> ejemplo: (name, valorDTO)
            if(StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            // Adding age specificaction
            // IF Hay algo -> Comparar "age" con el INT pasado en DTO.
            if(filtersDTO.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), filtersDTO.getAge())								);
            }
            //Pegamos las tablas (Character y Movie) (Hibernate se encarga de encontrarlas
            //a partir de las entidades)
            //Tomamos el ID de movie, para cada una de las relaciones existentes, y lo guardamos (moviesID)
            //Add -> si dicho moviesID, coincide con el del DTO Filtrado.
            if(!CollectionUtils.isEmpty(filtersDTO.getMovies())) {
                Join<CharacterEntity, MovieEntity> join = root.join("characterMovies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }

            // Removemos Duplicados:
            query.distinct(true);
            // Damos un orden HARD CODEADO: ASC por NAME
            query.orderBy(criteriaBuilder.asc(root.get("name")));

            // MAIN RETURN:
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
