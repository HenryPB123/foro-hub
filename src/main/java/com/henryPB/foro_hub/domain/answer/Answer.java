package com.henryPB.foro_hub.domain.answer;

import com.henryPB.foro_hub.domain.topic.Topic;
import com.henryPB.foro_hub.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "Answer")
@Table(name = "answers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private Boolean solution;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime creationDate;

    public Answer(String message, Topic topic, User author) {
        this.message = message;
        this.topic = topic;
        this.author = author;
        this.solution = false;
    }

    public boolean isSolution() {
        return this.solution;
    }

    public void markAsSolution() {
        this.solution = true;
    }

    public void unmarkAsSolution() {
        this.solution = false;
    }


    public void updateMessage(String message) {
        this.message = message.toLowerCase();
    }
//
//    @PrePersist
//    private void prePersist() {
//        this.creationDate = LocalDateTime.now();
//        this.solution = false;
//    }

}
