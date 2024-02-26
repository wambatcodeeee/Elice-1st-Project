package com.example.firstproject.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardRequestDTO {
    private String boardTitle;
    private String content;

    @Builder
    public BoardRequestDTO(String boardTitle, String content) {
        this.boardTitle = boardTitle;
        this.content = content;
    }
}
