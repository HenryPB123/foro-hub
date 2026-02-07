package com.henryPB.foro_hub.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterCourseData(
        @NotBlank String name,
        @NotNull Category category
) {
}
