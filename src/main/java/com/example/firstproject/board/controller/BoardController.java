package com.example.firstproject.board.controller;

import com.example.firstproject.board.entity.Board;
import com.example.firstproject.board.entity.BoardDTO;
import com.example.firstproject.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
        return "board/boards";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/createBoard";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveBoard(@ModelAttribute("board") BoardDTO boardDTO) {
        boardService.saveBoard(boardDTO.toEntity());
        return "redirect:/boards";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveBoard_1(@RequestBody BoardDTO boardDTO) {
        boardService.saveBoard(boardDTO.toEntity());
        return "redirect:/boards";
    }

    @GetMapping("/{id}")
    public String getBoardById(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "boardDetail";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findBoardById(id);
        if (board == null) {
            return "error";
        }
        model.addAttribute("board", board);
        return "board/editBoard";
    }

    @PutMapping(value = "/{id}/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateBoard_1(@PathVariable("id") Long id, @RequestBody Board board) {
        board.update(id);
        boardService.updateBoard(board);
        return "redirect:/boards";
    }

    @PutMapping(value = "/{id}/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateBoard(@PathVariable("id") Long id, @ModelAttribute("board") Board board) {
        board.update(id);
        boardService.updateBoard(board);
        return "redirect:/boards";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoardById(id);
        return "redirect:/boards";
    }
}
