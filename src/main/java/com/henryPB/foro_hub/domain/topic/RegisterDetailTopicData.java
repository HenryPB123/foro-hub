package com.henryPB.foro_hub.domain.topic;

import java.time.LocalDateTime;

public record RegisterDetailTopicData(
        Long id,
        String title,
        String message,
        Status status,
        LocalDateTime creationDate
) {

    public RegisterDetailTopicData(Topic topic){
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                topic.getCreationDate()
        );
    }
}
