package com.example.firstproject.post.entity;

import com.example.firstproject.board.entity.BoardDTO;
import com.example.firstproject.comment.entity.CommentDTO;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long postId;
    private String recipeName;
    private String description;
}
