package com.TrungTinhBackend.edu_ai_backend.repository;

import com.TrungTinhBackend.edu_ai_backend.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository<Feedback,String> {
}
