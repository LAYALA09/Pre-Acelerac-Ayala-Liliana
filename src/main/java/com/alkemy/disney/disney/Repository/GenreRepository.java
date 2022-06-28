package com.alkemy.disney.disney.Repository;


import com.alkemy.disney.disney.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository <GenreEntity, Long>  {

}
