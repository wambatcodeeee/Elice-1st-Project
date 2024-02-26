package com.example.firstproject.post.controller;

import com.example.firstproject.board.dto.BoardResponseDTO;
import com.example.firstproject.comment.entity.Comment;
import com.example.firstproject.comment.service.CommentService;
import com.example.firstproject.post.dto.PostRequestDTO;
import com.example.firstproject.post.entity.Post;
import com.example.firstproject.post.entity.PostMapper;
import com.example.firstproject.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, PostMapper postMapper, CommentService commentService){
        this.postService = postService;
        this.postMapper = postMapper;
        this.commentService = commentService;
    }

    @GetMapping("/{postId}")
    public String getPostDetail(@PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        List<Comment> comments = commentService.findCommentsByPostId(postId);
        model.addAttribute("comments", comments);
        return "post/post";
    }

    @GetMapping("/create")
    public String createPost(@RequestParam Long boardId, Model model) {
        model.addAttribute("boardId", boardId);
        return "post/createPost";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createPostPost(@ModelAttribute PostRequestDTO postRequestDTO, @RequestParam Long boardId) {
        Post post = postMapper.postRequestDTOToPost(postRequestDTO);
        Post createdPost = postService.createPost(post, boardId);
        return "redirect:/boards/" + createdPost.getBoard().getId();
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createPostPost_1(@RequestBody PostRequestDTO postRequestDTO, @RequestParam Long boardId) {
        Post post = postMapper.postRequestDTOToPost(postRequestDTO);
        Post createdPost = postService.createPost(post, boardId);
        return "redirect:/boards/" + createdPost.getBoard().getId();
    }

    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        return "post/editPost";
    }

    @PostMapping(value = "/{postId}/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editPost(@PathVariable Long postId, @ModelAttribute PostRequestDTO postRequestDTO, RedirectAttributes redirectAttributes) {
        Post post = postMapper.postRequestDTOToPost(postRequestDTO);
        Post updatedPost = postService.updatePost(post, postId);

        redirectAttributes.addAttribute("postId", updatedPost.getId());
        redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
        return "redirect:/posts/{postId}";
    }

    @PostMapping(value = "/{postId}/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String editPost_1(@PathVariable Long postId, @RequestBody PostRequestDTO postRequestDTO, RedirectAttributes redirectAttributes) {
        Post post = postMapper.postRequestDTOToPost(postRequestDTO);
        Post updatedPost = postService.updatePost(post, postId);

        redirectAttributes.addAttribute("postId", updatedPost.getId());
        redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
        return "redirect:/posts/{postId}";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId, RedirectAttributes redirectAttributes) {
        postService.deletePost(postId);
        redirectAttributes.addFlashAttribute("message", "과목이 제거되었습니다.");
        return "redirect:/posts";
    }
}
