package com.example.firstproject.board.entity;

import com.example.firstproject.post.entity.PostDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String content;

    @Builder
    public BoardDTO(Long id, String boardTitle, String content) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.content = content;
    }
}
