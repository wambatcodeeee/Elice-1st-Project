package com.example.firstproject.comment.entity;

import com.example.firstproject.post.entity.PostDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long commentId;
    private String content;
    private PostDTO post;
}
