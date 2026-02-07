package com.henryPB.foro_hub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserData(
        @NotBlank  @Email String email,
        @NotBlank  String password,
        Profile profile
) {
}
