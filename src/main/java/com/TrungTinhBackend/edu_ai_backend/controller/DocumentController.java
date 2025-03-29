package com.TrungTinhBackend.edu_ai_backend.controller;

import com.TrungTinhBackend.edu_ai_backend.model.Document;
import com.TrungTinhBackend.edu_ai_backend.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents/")
public class DocumentController {

    @Autowired
    private AIService aiService;

    public DocumentController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam List<MultipartFile> files, @RequestParam String userId) {
        try {
            aiService.classifyDocuments(files, userId);
            return ResponseEntity.ok("Document uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving document");
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Document> getDocument(@PathVariable String id) {
//        return documentService.getDocumentById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
}
