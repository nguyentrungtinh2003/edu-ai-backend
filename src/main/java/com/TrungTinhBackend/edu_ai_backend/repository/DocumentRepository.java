package com.TrungTinhBackend.edu_ai_backend.repository;

import com.TrungTinhBackend.edu_ai_backend.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<Document,String> {
}
