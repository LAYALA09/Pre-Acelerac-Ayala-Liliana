package com.alkemy.disney.disney.Repository;

import com.alkemy.disney.disney.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    // Método para buscarlos a todos los que cumplan con ciertas especificaciones o filtros
    List<CharacterEntity> findAll(Specification<CharacterEntity> spec);
}