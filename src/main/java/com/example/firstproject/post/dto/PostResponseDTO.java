package com.example.firstproject.post.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private String password;

    @Builder
    public PostResponseDTO(Long id, String title, String content, String userId, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.password = password;
    }
}
