package com.example.demoweb.repository;

import com.example.demoweb.entity.Cats;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CatRepository extends JpaRepository<Cats,Long> {

    @NewSpan
    Optional<Cats> findById(
            @SpanTag("catId")
                    Long id);
}
