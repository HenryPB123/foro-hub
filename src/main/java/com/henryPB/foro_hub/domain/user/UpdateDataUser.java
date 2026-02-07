package com.henryPB.foro_hub.domain.user;

import jakarta.validation.constraints.NotNull;

public record UpdateDataUser(
        @NotNull Long id,
        String email,
        String password,
        Profile profile
) {
}
