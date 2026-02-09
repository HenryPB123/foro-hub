package com.henryPB.foro_hub.domain.answer;

import com.henryPB.foro_hub.domain.pageResponse.PageResponseData;

import com.henryPB.foro_hub.domain.topic.Topic;
import com.henryPB.foro_hub.domain.topic.TopicRepository;
import com.henryPB.foro_hub.domain.user.User;
import com.henryPB.foro_hub.domain.user.UserRepository;
import com.henryPB.foro_hub.infra.exceptions.BusinessRuleException;
import com.henryPB.foro_hub.infra.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public Answer createAnswer(Long id, RegisterAnswerData data) {

        Topic topic = topicRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("El tópico no existe"));

        User author = userRepository.findById(data.authorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("El usuario no existe"));

        Answer answer = new Answer(
                data.message(),
                topic,
                author
        );

        return answerRepository.save(answer);
    }


    public PageResponseData<AnswerDetailData> listByTopic(Long topicId, Pageable pageable) {

        if (!topicRepository.existsById(topicId)) {
            throw new ResourceNotFoundException(
                    "No existe un tópico con el id proporcionado"
            );
        }

        Page<Answer> page = answerRepository
                .findAllByTopicId(topicId, pageable);

        if(page.isEmpty()){
            throw new ResourceNotFoundException("Aún no hay respuestas para este tópico");
        }

        Page<AnswerDetailData> dtoPage =
                page.map(AnswerDetailData::new);

        return new PageResponseData<>(dtoPage);
    }


    public AnswerDetailData updateAnswer(Long answerId, UpdateDataAswer data, Long authenticatedUserId) {

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe una respuesta con este id"));

        //  Regla de negocio: solo el autor puede editar
        if (!answer.getAuthor().getId().equals(authenticatedUserId)) {
            throw new BusinessRuleException("No tienes permiso para editar esta respuesta");
        }

        answer.updateMessage(data.message());

        answerRepository.save(answer);

        return new AnswerDetailData(answer);
    }

    public String markAsSolution(Long answerId, User loggedUser) {

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Respuesta no encontrada"));

        Topic topic = answer.getTopic();

        if (!topic.getAuthor().getId().equals(loggedUser.getId())) {
            throw new BusinessRuleException("Solo el autor del tópico puede marcar la solución");
        }

        // Desmarcar solución anterior si existe
        answerRepository.findByTopicAndSolutionTrue(topic)
                .ifPresent(Answer::unmarkAsSolution);

        answer.markAsSolution();

        return "Answer marcada como solucionada";
    }


    @Transactional
    public String delete(Long answerId, User loggedUser) {

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Respuesta no encontrada"));

        boolean isAuthor =
                answer.getAuthor().getId().equals(loggedUser.getId());

        boolean isTopicOwner =
                answer.getTopic().getAuthor().getId().equals(loggedUser.getId());

        if (!isAuthor && !isTopicOwner) {
            throw new BusinessRuleException("No tienes permiso para eliminar esta respuesta");
        }

        answerRepository.delete(answer);

        return "Answer eliminada exitosamente";
    }

}



