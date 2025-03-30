package com.TrungTinhBackend.edu_ai_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "models")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AIModel {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
    private String version;
}
