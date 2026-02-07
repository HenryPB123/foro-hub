package com.henryPB.foro_hub.domain.course;

import com.henryPB.foro_hub.domain.user.User;

public record RegisterDetailCourseData(
        Long id,
        String name,
        Category category
) {

    public RegisterDetailCourseData(Course course) {
        this(
                course.getId(),
                course.getName(),
                course.getCategory()
        );
    }
}
