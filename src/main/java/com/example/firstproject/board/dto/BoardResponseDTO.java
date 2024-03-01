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
    private String filename;
    private String filepath;
    private String userId;
    private String password;

    @Builder
    public BoardResponseDTO(Long id, String boardTitle, String content, String filename, String filepath, String userId,
                            String password) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.content = content;
        this.filename = filename;
        this.filepath = filepath;
        this.userId = userId;
        this.password = password;
    }
}
