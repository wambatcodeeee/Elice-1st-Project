package com.example.firstproject.board.repository;

import com.example.firstproject.board.entity.Board;

import java.util.List;

public interface BoardRepository {
    void save(Board board);
    void update(Board board);
    void deleteById(Long boardId);
    Board findById(Long boardId);
    List<Board> findAll();
}
