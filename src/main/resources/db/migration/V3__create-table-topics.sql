CREATE TABLE topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL COLLATE utf8mb4_unicode_ci,
    message TEXT NOT NULL,
    creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    status ENUM('OPEN', 'CLOSED', 'SOLVED') NOT NULL DEFAULT 'OPEN',

    author_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT uk_topics_title UNIQUE (title),

    CONSTRAINT fk_topics_author
        FOREIGN KEY (author_id) REFERENCES users(id),

    CONSTRAINT fk_topics_course
        FOREIGN KEY (course_id) REFERENCES courses(id),

    INDEX idx_topics_author (author_id),
    INDEX idx_topics_course (course_id),
    INDEX idx_topics_status (status)
) ENGINE=InnoDB;