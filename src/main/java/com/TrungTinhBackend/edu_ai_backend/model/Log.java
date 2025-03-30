package com.TrungTinhBackend.edu_ai_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@org.springframework.data.mongodb.core.mapping.Document(collection = "logs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    @Id
    private String id;

    @Indexed(unique = true)
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public AIModel getModel() {
        return model;
    }

    public void setModel(AIModel model) {
        this.model = model;
    }

    private LocalDateTime timestamp;

    @DBRef
    private Document document;

    @DBRef
    private AIModel model;
}
