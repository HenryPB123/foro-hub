package com.henryPB.foro_hub.domain.answer;

import java.time.LocalDateTime;

public record AnswerDetailData(
        Long id,
        String message,
        boolean solution,
        LocalDateTime creationDate,
        Long topicId,
        Long authorId
) {
    public AnswerDetailData(Answer answer) {
        this(
                answer.getId(),
                answer.getMessage(),
                answer.isSolution(),
                answer.getCreationDate(),
                answer.getTopic().getId(),
                answer.getAuthor().getId()
        );
    }
}

