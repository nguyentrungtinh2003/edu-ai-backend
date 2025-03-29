package com.TrungTinhBackend.edu_ai_backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Data
public class Category {
    @Id
    private String id;
    private String name;
}
