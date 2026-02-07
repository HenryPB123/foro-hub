CREATE TABLE answers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    message TEXT NOT NULL,

    solution BOOLEAN NOT NULL DEFAULT FALSE,
    creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    topic_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_answers_topic
        FOREIGN KEY (topic_id) REFERENCES topics(id),

    CONSTRAINT fk_answers_author
        FOREIGN KEY (author_id) REFERENCES users(id),

    INDEX idx_answers_topic (topic_id),
    INDEX idx_answers_author (author_id),
    INDEX idx_answers_solution (solution)
) ENGINE=InnoDB;