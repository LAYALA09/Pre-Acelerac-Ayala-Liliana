package com.alkemy.disney.disney.Repository;

import com.alkemy.disney.disney.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    // Method to search for all those who meet certain specifications or filters
    List<CharacterEntity> findAll(Specification<CharacterEntity> spec);
}