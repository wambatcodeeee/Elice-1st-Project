package com.example.firstproject.comment.service;

import com.example.firstproject.Exception.Exception;
import com.example.firstproject.Exception.ExceptionEnum;
import com.example.firstproject.comment.entity.Comment;
import com.example.firstproject.comment.repository.CommentRepository;
import com.example.firstproject.post.entity.Post;
import com.example.firstproject.post.repository.PostRepository;
import com.example.firstproject.user.entity.User;
import com.example.firstproject.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserService userService){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void saveComment(Long postId, Comment comment, String userId){
        User user = userService.findUserByUserId(userId);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new Exception(ExceptionEnum.POST_NOT_FOUND));
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);
    }

    public Long findPostIdById(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new Exception(ExceptionEnum.COMMENT_NOT_FOUND));
        return comment.getPost().getId();
    }

    public List<Comment> findCommentsByPostId(Long postId){
        return commentRepository.findCommentsByPostId(postId);
    }

    public Comment updateComment(Long commentId, Comment comment, String userId){
        User user = userService.findUserByUserId(userId);
        Comment lastComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception(ExceptionEnum.COMMENT_NOT_FOUND));
        Optional.ofNullable(comment.getContent())
                .ifPresent(lastComment::setContent);
        lastComment.setUser(user);

        return commentRepository.save(lastComment);
    }

    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception(ExceptionEnum.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
    }
}
