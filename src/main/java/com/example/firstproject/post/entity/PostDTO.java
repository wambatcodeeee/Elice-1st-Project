package com.example.firstproject.post.entity;

import com.example.firstproject.board.entity.BoardDTO;
import com.example.firstproject.comment.entity.CommentDTO;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String content;

    @Builder
    public PostDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
