package com.example.firstproject.board.controller;

import com.example.firstproject.board.entity.Board;
import com.example.firstproject.board.entity.BoardDTO;
import com.example.firstproject.board.entity.BoardMapper;
import com.example.firstproject.board.service.BoardService;
import com.example.firstproject.post.entity.Post;
import com.example.firstproject.post.service.PostService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final BoardMapper boardMapper;
    private final PostService postService;

    @Autowired
    public BoardController(BoardService boardService, BoardMapper boardMapper, PostService postService) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
        this.postService = postService;
    }

    @GetMapping
    public String getAllBoards(Model model) {
        List<Board> boards = boardService.findAllBoards();
        model.addAttribute("boards", boards);
        return "board/boards";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(required = false) String keyword,
                           Model model) {
        Board board = boardService.findBoardById(id);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> postPage = postService.findPostsByBoardAndKeyword(board, keyword, pageRequest);

        model.addAttribute("board", board);
        model.addAttribute("keyword", keyword);
        model.addAttribute("postPage", postPage);
        return "board/board";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/createBoard";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveBoard(@ModelAttribute("board") BoardDTO boardDTO) {
        Board board = boardMapper.boardDTOToBoard(boardDTO);
        boardService.saveBoard(board);
        return "redirect:/boards";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveBoard_1(@RequestBody BoardDTO boardDTO) {
        Board board = boardMapper.boardDTOToBoard(boardDTO);
        boardService.saveBoard(board);
        return "redirect:/boards";
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

    @PostMapping(value = "/{id}/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateBoard(@PathVariable("id") Long id, @ModelAttribute("board") BoardDTO boardDTO) {
        Board board = boardMapper.boardDTOToBoard(boardDTO).toBuilder().id(id).build();
        boardService.updateBoard(board);
        return "redirect:/boards";
    }

    @PostMapping(value = "/{id}/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateBoard_1(@PathVariable("id") Long id, @RequestBody Board board) {
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
