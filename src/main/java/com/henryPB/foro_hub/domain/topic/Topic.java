package com.henryPB.foro_hub.domain.topic;


import com.henryPB.foro_hub.domain.answer.Answer;
import com.henryPB.foro_hub.domain.course.Course;
import com.henryPB.foro_hub.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topic")
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer>  answers;

    public Topic(@NotBlank String title, @NotBlank String message, User author, Course course) {
        this.title = title;
        this.message = message;
        this.author = author;
        this.course = course;
        this.status = Status.OPEN;
    }

    public void update(String title, String message, Status status) {
        if (title != null)this.title = title;
        if (message != null)this.message = message;
        if (status != null)this.status = status;
    }

    public void closeTopic() {
        this.status = Status.CLOSED;
    }


//
//    @PrePersist
//    private void prePersist() {
//        this.creationDate = LocalDateTime.now();
//        this.status = Status.OPEN;
//    }



}
