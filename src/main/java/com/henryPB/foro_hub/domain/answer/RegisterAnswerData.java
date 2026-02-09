package com.henryPB.foro_hub.domain.answer;

public record RegisterAnswerData(
        String message,
        Long authorId,
        Long topicId
) {
}
