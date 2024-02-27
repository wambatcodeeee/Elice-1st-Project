package com.example.firstproject.board.service;

import com.example.firstproject.board.entity.Board;
import com.example.firstproject.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileService boardFileService;

    @Autowired
    public BoardService(BoardRepository boardRepository,BoardFileService boardFileService){
        this.boardRepository = boardRepository;
        this.boardFileService = boardFileService;
    }

    public void saveBoard(Board board, MultipartFile file) throws IOException {
        board = boardFileService.fileUpload(file, board);
        boardRepository.save(board);
    }

    public void updateBoard(Board board, MultipartFile file) throws IOException {
        board = boardFileService.fileUpload(file, board);
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
