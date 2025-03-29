package com.TrungTinhBackend.edu_ai_backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@org.springframework.data.mongodb.core.mapping.Document(collection = "feedbacks")
@Getter
@Setter
public class Feedback {
    @Id
    private String id;
    private String comment;
    private int rating; // 1-5 stars
    private LocalDateTime createdAt;

    @DBRef
    private Document document;

    @DBRef
    private User user;
}
