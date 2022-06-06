package com.alkemy.disney.disney.Repository;

import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Long> {
}