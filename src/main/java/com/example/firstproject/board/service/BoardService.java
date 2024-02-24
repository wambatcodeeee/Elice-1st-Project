package com.example.firstproject.board.service;

import com.example.firstproject.board.entity.Board;
import com.example.firstproject.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    public void updateBoard(Board board) {
        boardRepository.update(board);
    }

    public void deleteBoardById(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public Board findBoardById(Long boardId) {
        return boardRepository.findById(boardId);
    }

    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }
}
