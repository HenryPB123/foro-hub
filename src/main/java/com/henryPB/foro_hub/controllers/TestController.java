package com.henryPB.foro_hub.controllers;

import com.henryPB.foro_hub.domain.user.AuthenticationData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@SecurityRequirement(name="bearer-key")//*necesaria para que en la dovcumentación de swagger me permita agregar el campo de Authorization
public class TestController {

    @GetMapping
    public String login(){
            return "Hello TESTEANDO!!!!!!!!!!!";
    }
}
