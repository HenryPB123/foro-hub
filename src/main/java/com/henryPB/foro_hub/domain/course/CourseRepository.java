package com.henryPB.foro_hub.domain.course;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findAllById(@NotNull Long id);


    boolean existsByName(String name);
}
