package com.henryPB.foro_hub.domain.topic;

import java.time.LocalDateTime;

public record UpdateDataTopic(
        Long id,
        String title,
        String message,
        Status status
)  {

}