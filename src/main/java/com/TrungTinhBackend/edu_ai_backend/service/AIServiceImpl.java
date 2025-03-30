package com.TrungTinhBackend.edu_ai_backend.service;

import com.TrungTinhBackend.edu_ai_backend.model.Category;
import com.TrungTinhBackend.edu_ai_backend.model.Document;
import com.TrungTinhBackend.edu_ai_backend.model.User;
import com.TrungTinhBackend.edu_ai_backend.repository.DocumentRepository;
import com.TrungTinhBackend.edu_ai_backend.repository.UserRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class AIServiceImpl implements AIService {

    @Value("${ai.api.key}")
    private String apiKey;

    @Value("${ai.api.url}")
    private String apiUrl;

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<String> classifyDocuments(List<MultipartFile> files, String userId) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found!"));

        List<String> categories = new ArrayList<>();
        HttpHeaders headers = createHeaders();

        for (MultipartFile file : files) {
            String text = extractTextFromFile(file);
            if (text.isEmpty()) {
                categories.add("Không thể đọc nội dung");
                continue;
            }

            categories.add(sendTextToAI(text, headers));
        }

        saveDocuments(user, files, categories);
        return categories;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private String sendTextToAI(String text, HttpHeaders headers) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "deepseek-chat");

        JSONObject systemMessage = new JSONObject();
        systemMessage.put("role", "system");
        systemMessage.put("content", "Bạn là AI chuyên phân loại tài liệu thành các danh mục: Công nghệ, Khoa học, Kinh tế, Sức khỏe.");

        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", "Hãy phân loại tài liệu sau: \n\n" + text);

        requestBody.put("messages", List.of(systemMessage, userMessage));
        requestBody.put("stream", false);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        System.out.println("AI Response: " + response.getBody()); // Thêm dòng này để debug

        JSONObject responseJson = new JSONObject(response.getBody());

        if (responseJson.has("choices")) {
            return responseJson.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
        } else {
            return "Không nhận được phản hồi phù hợp từ AI.";
        }
    }

    private void saveDocuments(User user, List<MultipartFile> files, List<String> categories) {
        for (int i = 0; i < files.size(); i++) {
            Document document = new Document();
            document.setUploadedBy(user);

            Category category = new Category();
            category.setName(categories.get(i));
            document.getCategories().add(category);

            documentRepository.save(document);
        }
    }

    private String extractTextFromFile(MultipartFile file) throws IOException {
        String contentType = file.getContentType();

        return switch (contentType) {
            case "application/pdf" -> extractTextFromPDF(file);
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> extractTextFromWord(file);
            default -> contentType.startsWith("image/") ? "OCR chưa được triển khai" : new String(file.getBytes(), StandardCharsets.UTF_8);
        };
    }

    private String extractTextFromPDF(MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            return new PDFTextStripper().getText(document);
        }
    }

    private String extractTextFromWord(MultipartFile file) throws IOException {
        try (InputStream is = file.getInputStream(); XWPFDocument doc = new XWPFDocument(is)) {
            StringBuilder text = new StringBuilder();
            for (XWPFParagraph para : doc.getParagraphs()) {
                text.append(para.getText()).append("\n");
            }
            return text.toString();
        }
    }
}
