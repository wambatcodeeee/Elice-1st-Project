package com.example.firstproject.board.entity;

import com.example.firstproject.post.entity.PostDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class BoardDTO {
    private Long boardId;
    private String boardTitle;
    private String content;
    private List<PostDTO> posts;
}
