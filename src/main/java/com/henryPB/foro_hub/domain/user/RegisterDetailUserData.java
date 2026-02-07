package com.henryPB.foro_hub.domain.user;

public record RegisterDetailUserData(
        Long id,
        String email,
        Profile profile
) {

    public RegisterDetailUserData(User user){
        this(
                user.getId(),
                user.getEmail(),
                user.getProfile()
        );
    }
}
