package com.henryPB.foro_hub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RegisterTopicData(
        @NotNull Long userId,
        @NotNull Long courseId,
        @NotBlank String title,
        @NotBlank String message
        ) {
}
