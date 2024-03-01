package com.example.firstproject.comment.entity;

import lombok.*;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;

    @Builder
    public CommentDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
