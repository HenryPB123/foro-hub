package com.henryPB.foro_hub.domain.answer;

import com.henryPB.foro_hub.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository  extends JpaRepository<Answer, Long> {

    Page<Answer> findAllByTopicId(Long id, Pageable pageable);

    Optional<Answer> findByTopicAndSolutionTrue(Topic topic);

}
