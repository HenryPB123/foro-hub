package com.henryPB.foro_hub.domain.course;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Course")
@Table(name = "courses",
        indexes = {
                @Index(name = "idx_courses_category", columnList = "category")
        }
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    public Course(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public void update(String name, Category category) {
        if(name != null) this.name = name;
        if(category != null) this.category = category;
    }

}
