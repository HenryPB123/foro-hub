package com.henryPB.foro_hub.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name="bearer-key")
public class TopicController {

    @GetMapping
    private String topic(){
        return "Probando la ruta de los tópicos";
    }

}
