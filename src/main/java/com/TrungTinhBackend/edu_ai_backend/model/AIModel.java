package com.TrungTinhBackend.edu_ai_backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "models")
@Data
public class AIModel {
    @Id
    private String id;
    private String name;
    private String version;
}
