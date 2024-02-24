package com.example.firstproject.board.entity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface BoardMapper {

    Board boardDTOToBoard(BoardDTO boardDTO);
}
