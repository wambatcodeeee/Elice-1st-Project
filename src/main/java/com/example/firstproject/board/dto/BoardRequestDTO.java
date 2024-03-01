package com.example.firstproject.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardRequestDTO {
    private String boardTitle;
    private String content;
    private String userId;

    @Builder
    public BoardRequestDTO(String boardTitle, String content, String userId) {
        this.boardTitle = boardTitle;
        this.content = content;
        this.userId = userId;
    }
}
