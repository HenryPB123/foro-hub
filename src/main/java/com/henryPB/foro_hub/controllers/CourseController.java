package com.henryPB.foro_hub.controllers;

import com.henryPB.foro_hub.domain.course.*;
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
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    //Create Course
    @Transactional
    @PostMapping
    public ResponseEntity createCourse(@RequestBody @Valid RegisterCourseData data, UriComponentsBuilder uriBuilder){

        Course course = courseService.createCourse(data);

        var uri = uriBuilder
                .path("/courses/{id}")
                .buildAndExpand(course.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new RegisterDetailCourseData(course));
    }

    //Get all courses
    @GetMapping
    public ResponseEntity<PageResponseData<RegisterDetailCourseData>> getCourses(@PageableDefault(size = 10) Pageable pageable){
        var responsePageData = courseService.getAllCourses(pageable);
        return ResponseEntity.ok(responsePageData);
    }

    //Get one course
    @GetMapping("/{id}")
    public ResponseEntity getCourse(@PathVariable Long id){

        Course course = courseService.findCourseById(id);
        return ResponseEntity.ok(new RegisterDetailCourseData(course));
    }

    //Update course
    @Transactional
    @PutMapping
    public ResponseEntity updateCourse(@RequestBody @Valid UpdateDataCourse data){
        Course course = courseService.updateCourse(data);
        return ResponseEntity.ok(new RegisterDetailCourseData(course));
    }

    //Delete or desactivate user
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id){
        var response = courseService.deleteCourse(id);
        return ResponseEntity.ok(response);
    }
}
