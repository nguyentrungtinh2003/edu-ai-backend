package com.TrungTinhBackend.edu_ai_backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AIService {
     List<String> classifyDocuments(List<MultipartFile> files, String userId) throws IOException;
}
