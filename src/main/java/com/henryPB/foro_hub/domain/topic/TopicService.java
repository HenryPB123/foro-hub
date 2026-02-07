package com.henryPB.foro_hub.domain.topic;

import com.henryPB.foro_hub.domain.course.Course;
import com.henryPB.foro_hub.domain.course.CourseRepository;
import com.henryPB.foro_hub.domain.pageResponse.PageResponseData;
import com.henryPB.foro_hub.domain.user.User;
import com.henryPB.foro_hub.domain.user.UserRepository;
import com.henryPB.foro_hub.infra.exceptions.ResourceAlreadyExistsException;
import com.henryPB.foro_hub.infra.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(@Valid RegisterTopicData data) {
        if(topicRepository.existsByTitle(data.title())){
            throw new ResourceAlreadyExistsException("Un tópico con este título ya existe!");
        }

        User user = userRepository.findById(data.userId())
                .orElseThrow(()-> new ResourceNotFoundException("El id de este usuario no se encuentra registrado, por tanto, no se puede crear el tópico"));

        Course course = courseRepository.findById(data.courseId())
                .orElseThrow(()-> new ResourceNotFoundException("No existe un curso que corresponda con el id, por tanto, no se puede crear el tópico"));

        Topic topic = new Topic(
                data.title().toLowerCase(),
                data.message(),
                user,
                course
        );

        return topicRepository.save(topic);
    }

    public PageResponseData<RegisterDetailTopicData>getAllTopics(Pageable pageable) {

        Page<Topic> page = topicRepository.findAllByStatus(Status.OPEN, pageable);

        if(page.isEmpty()){
            throw new ResourceNotFoundException("Aún no se han creado tópicos");
        }

        Page<RegisterDetailTopicData> dtoPage =
                page.map(RegisterDetailTopicData::new);

        return new PageResponseData<>(dtoPage);
    }

    public Topic findTopicById(Long id) {
       return topicRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No hay un tópico con este id"));
    }


    public Topic updateTopic(@Valid UpdateDataTopic data) {
       Topic topic = topicRepository.findById(data.id())
                .orElseThrow(()->new ResourceNotFoundException("No existe un tópico con este Id"));

        topic.update(
                data.title(),
                data.message(),
                data.status()
        );

        return topic;
    }

    public Object closeTopic(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe un tópico con este Id"));

        topic.closeTopic();

        return "Tópico cerrado";
    }
}
