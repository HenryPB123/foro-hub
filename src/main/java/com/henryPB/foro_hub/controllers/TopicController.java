package com.henryPB.foro_hub.controllers;

import com.henryPB.foro_hub.domain.course.CourseRepository;
import com.henryPB.foro_hub.domain.pageResponse.PageResponseData;
import com.henryPB.foro_hub.domain.topic.*;
import com.henryPB.foro_hub.domain.user.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name="bearer-key")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AuthenticationService authService;

    //CreaTE topic
    @Transactional
    @PostMapping
    public ResponseEntity<RegisterDetailTopicData> createUser(@RequestBody @Valid RegisterTopicData data, UriComponentsBuilder uriBuilder){

        Topic topic = topicService.createTopic(data);

        var uri = uriBuilder
                .path("/topics/{id}")
                .buildAndExpand(topic.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new RegisterDetailTopicData(topic));
    }

    //Get all topics
    @GetMapping
    public ResponseEntity<PageResponseData<RegisterDetailTopicData>> getTopics(@PageableDefault(size = 10) Pageable pageable){
        var responsePageData = topicService.getAllTopics(pageable);
        return ResponseEntity.ok(responsePageData);
    }

    //Get one topic
    @GetMapping("/{id}")
    public ResponseEntity getTopic(@PathVariable Long id){
        Topic topic = topicService.findTopicById(id);
        return ResponseEntity.ok(new RegisterDetailTopicData(topic));
    }

    //Update topic
    @Transactional
    @PutMapping
    public ResponseEntity updateTopic(@RequestBody @Valid UpdateDataTopic data){
        Topic topic = topicService.updateTopic(data);
        return ResponseEntity.ok(new RegisterDetailTopicData(topic));
    }

    //Delete or File topic
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity filedTopic(@PathVariable Long id){
        var response = topicService.closeTopic(id);
        return ResponseEntity.ok(response);
    }

}
