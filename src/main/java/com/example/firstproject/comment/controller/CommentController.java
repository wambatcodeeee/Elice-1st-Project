package com.example.firstproject.comment.controller;

import com.example.firstproject.comment.entity.Comment;
import com.example.firstproject.comment.entity.CommentDTO;
import com.example.firstproject.comment.entity.CommentMapper;
import com.example.firstproject.comment.service.CommentService;
import com.example.firstproject.user.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final UserService userService;

    public CommentController(CommentService commentService, CommentMapper commentMapper, UserService userService){
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.userService = userService;
    }

    @PostMapping
    public String saveComment(@ModelAttribute CommentDTO commentDTO, @RequestParam Long postId,
                              String userId, RedirectAttributes redirectAttributes){
        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        if(userService.validation(userId)){
            commentService.saveComment(postId, comment, userId);
        }
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    /**@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveComment_1(@RequestBody CommentDTO commentDTO, @RequestParam Long postId, RedirectAttributes redirectAttributes){
        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        commentService.saveComment(postId, comment);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }**/

    @PostMapping(value = "/{commentId}/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateComment(@PathVariable Long commentId, @ModelAttribute CommentDTO commentDTO,
                                RedirectAttributes redirectAttributes){
        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        redirectAttributes.addAttribute("postId", commentService.updateComment(commentId, comment).getPost().getId());
        return "redirect:/posts/{postId}";
    }

    /**@PostMapping(value = "/{commentId}/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateComment_1(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO,
                                RedirectAttributes redirectAttributes){
        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        redirectAttributes.addAttribute("postId", commentService.updateComment(commentId, comment).getPost().getId());
        return "redirect:/posts/{postId}";
    }**/

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/posts";
    }
}
