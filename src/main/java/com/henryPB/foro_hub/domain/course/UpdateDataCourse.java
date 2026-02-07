package com.henryPB.foro_hub.domain.course;

import jakarta.validation.constraints.NotNull;

public record UpdateDataCourse(
        @NotNull Long id,
        String name,
        Category category
) {
}
