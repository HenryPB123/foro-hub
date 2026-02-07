package com.henryPB.foro_hub.controllers;

import com.henryPB.foro_hub.domain.pageResponse.PageResponseData;
import com.henryPB.foro_hub.domain.user.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "beare-key")
public class UserController {

    @Autowired
    private UserService userService;

    //Create User
    @Transactional
    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid RegisterUserData data, UriComponentsBuilder uriBuilder){

        User user = userService.createUser(data);
        var uri = uriBuilder
                .path("/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new RegisterDetailUserData(user));
    }

    //Get all users
    @GetMapping
    public ResponseEntity<PageResponseData<RegisterDetailUserData>> getUsers(@PageableDefault(size = 10) Pageable pageable){
        var responsePageData = userService.getAllUsers(pageable);
        return ResponseEntity.ok(responsePageData);
    }

    //Get one user
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id){
        User user = userService.findUserById(id);
        return ResponseEntity.ok(new RegisterDetailUserData(user));
    }

    //Update user
    @Transactional
    @PutMapping
    public ResponseEntity updateUser(@RequestBody @Valid UpdateDataUser data){
        User user = userService.updateUser(data);
        return ResponseEntity.ok(new RegisterDetailUserData(user));
    }

    //Delete or desactivate user
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        var response = userService.desactivateUser(id);
        return ResponseEntity.ok(response);
    }
}
