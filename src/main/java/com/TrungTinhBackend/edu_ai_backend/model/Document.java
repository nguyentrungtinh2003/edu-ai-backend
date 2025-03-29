package com.TrungTinhBackend.edu_ai_backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@org.springframework.data.mongodb.core.mapping.Document(collection = "documents")
public class Document {
    @Id
    private String id;
    private String title;
    private String content;
    private String fileUrl;
    private LocalDateTime uploadedAt;

    @DBRef
    private User uploadedBy;

    @DBRef
    private List<Label> labels;

    @DBRef
    private List<Category> categories;

    @DBRef
    private List<Log> logs;

    @DBRef
    private List<Feedback> feedbacks;
}
