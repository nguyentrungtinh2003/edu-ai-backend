package com.TrungTinhBackend.edu_ai_backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "labels")
public class Label {
    @Id
    private String id;
    private String name;
    private String description;
}
