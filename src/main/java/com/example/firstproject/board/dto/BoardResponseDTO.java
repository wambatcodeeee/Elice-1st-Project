package com.example.firstproject.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardResponseDTO {
    private Long id;
    private String boardTitle;
    private String content;

    @Builder
    public BoardResponseDTO(Long id, String boardTitle, String content) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.content = content;
    }
}
