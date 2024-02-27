package com.example.firstproject.board.entity;

import com.example.firstproject.board.dto.BoardRequestDTO;
import com.example.firstproject.board.dto.BoardResponseDTO;

@org.mapstruct.Mapper(componentModel = "spring")
public interface BoardMapper {

    Board boardRequestDTOToBoard(BoardRequestDTO boardRequestDTO);
    BoardResponseDTO boardToBoardResponseDTO(Board board);
}
