package com.henryPB.foro_hub.controllers;

import com.henryPB.foro_hub.domain.user.AuthenticationData;
import com.henryPB.foro_hub.domain.user.User;
import com.henryPB.foro_hub.infra.security.TokenJWTData;
import com.henryPB.foro_hub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationData data ){

        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        System.out.println("TOKEEEEEEEEEEEEEEEEEEEEEEEENNNNNNNNNNN => " + tokenJWT);

        return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }


}
