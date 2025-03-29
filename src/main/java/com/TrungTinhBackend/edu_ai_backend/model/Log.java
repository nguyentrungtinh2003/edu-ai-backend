package com.TrungTinhBackend.edu_ai_backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@org.springframework.data.mongodb.core.mapping.Document(collection = "logs")
@Data
public class Log {
    @Id
    private String id;
    private String message;
    private LocalDateTime timestamp;

    @DBRef
    private Document document;

    @DBRef
    private AIModel model;
}
