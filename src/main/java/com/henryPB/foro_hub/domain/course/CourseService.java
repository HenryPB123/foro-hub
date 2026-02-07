package com.henryPB.foro_hub.domain.course;

import com.henryPB.foro_hub.domain.pageResponse.PageResponseData;
import com.henryPB.foro_hub.domain.user.*;
import com.henryPB.foro_hub.infra.exceptions.ResourceAlreadyExistsException;
import com.henryPB.foro_hub.infra.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CourseService  {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(RegisterCourseData data) {
        if (courseRepository.existsByName(data.name())) {
            throw new ResourceAlreadyExistsException("Un curso con este nombre ya existe!");
        }
        Course course = new Course(
                data.name().toLowerCase(),
                data.category()
        );

        return courseRepository.save(course);
    }


    public PageResponseData<RegisterDetailCourseData> getAllCourses(Pageable pageable) {

        Page<Course> page = courseRepository.findAll(pageable);

        // Control de error si no hay cursos
        if (page.isEmpty()) {
            throw new ResourceNotFoundException("El recurso solicitado no existe");
        }

        Page<RegisterDetailCourseData> dtoPage =
                page.map(RegisterDetailCourseData::new);

        return new PageResponseData<>(dtoPage);
    }


    public Course findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El recurso solicitado no existe"));
    }

    public Course updateCourse(UpdateDataCourse data){
        Course course = courseRepository.findAllById(data.id())
                .orElseThrow(()-> new ResourceNotFoundException("El recurso solicitado no existe"));

        course.update(
                data.name(),
                data.category()
        );
        return course;
    }

    public String deleteCourse(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El recurso solicitado no existe"));

        courseRepository.deleteById(id);

        return "Curso eliminado";
    }
}

