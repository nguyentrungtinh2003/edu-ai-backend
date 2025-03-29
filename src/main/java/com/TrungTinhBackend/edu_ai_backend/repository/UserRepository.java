package com.TrungTinhBackend.edu_ai_backend.repository;

import com.TrungTinhBackend.edu_ai_backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
