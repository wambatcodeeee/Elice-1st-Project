package com.example.firstproject.post.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class PostRequestDTO {
    private String title;
    private String content;

    @Builder
    public PostRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}