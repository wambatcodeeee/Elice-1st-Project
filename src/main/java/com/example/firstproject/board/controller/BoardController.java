package com.example.firstproject.board.controller;

import com.example.firstproject.board.entity.Board;
import com.example.firstproject.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public String getAllBoards(Model model) {
        List<Board> boards = boardService.findAllBoards();
        model.addAttribute("boards", boards);
        return "boardList"; // HTML 파일의 이름
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("board", new Board());
        return "boardCreate";
    }

    @PostMapping("/create")
    public String saveBoard(@ModelAttribute("board") Board board) {
        boardService.saveBoard(board);
        return "redirect:/boards";
    }

    @GetMapping("/{id}")
    public String getBoardById(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "boardDetail"; // HTML 파일의 이름
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "boardEdit";
    }

    @PostMapping("/{id}/edit")
    public String updateBoard(@PathVariable("id") Long id, @ModelAttribute("board") Board board) {
        board.setBoardId(id);
        boardService.updateBoard(board);
        return "redirect:/boards";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoardById(id);
        return "redirect:/boards";
    }
}
