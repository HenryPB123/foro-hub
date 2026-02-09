package com.henryPB.foro_hub.controllers;


import com.henryPB.foro_hub.domain.answer.*;
import com.henryPB.foro_hub.domain.user.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AnswerDetailData> updateAnswer(@PathVariable Long id,@RequestBody @Valid UpdateDataAswer data, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
                answerService.updateAnswer(id, data, user.getId())
        );
    }

    @PutMapping("/{id}/solution")
    @Transactional
        public ResponseEntity<String> markAsSolution(@PathVariable Long id, @AuthenticationPrincipal User user) {
      var response = answerService.markAsSolution(id, user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
        public ResponseEntity<String> delete(@PathVariable Long id,@AuthenticationPrincipal User user) {
        var response = answerService.delete(id, user);
        return ResponseEntity.ok(response);
    }
}

