package com.henryPB.foro_hub.domain.answer;

import jakarta.validation.constraints.NotBlank;

public record UpdateDataAswer(
        @NotBlank
        String message
) {}
