package com.alkemy.disney.disney.Repository.specifications;

import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecifications {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList();

            // Adding title specificaction
            if (StringUtils.hasLength(filtersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }

            // Adding genre specificaction
            if (filtersDTO.getGenreId() != null) {
                predicates.add(
                        criteriaBuilder.equal((root.get("genreId")),
                                filtersDTO.getGenreId())
                );
            }

            // Remove duplicates
            query.distinct(true);

            // Order resolver
            String orderByField = "creationDate";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                           criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
