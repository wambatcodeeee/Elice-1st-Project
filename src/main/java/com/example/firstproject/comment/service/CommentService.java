package com.example.firstproject.comment.service;

import com.example.firstproject.Exception.Exception;
import com.example.firstproject.Exception.ExceptionEnum;
import com.example.firstproject.comment.entity.Comment;
import com.example.firstproject.comment.repository.CommentRepository;
import com.example.firstproject.post.entity.Post;
import com.example.firstproject.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public void saveComment(Long postId, Comment comment){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new Exception(ExceptionEnum.POST_NOT_FOUND));
        comment.setPost(post);
        commentRepository.save(comment);
    }

    public List<Comment> findCommentsByPostId(Long postId){
        return commentRepository.findCommentsByPostId(postId);
    }

    public Comment updateComment(Long commentId, Comment comment){
        Comment lastComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception(ExceptionEnum.COMMENT_NOT_FOUND));
        Optional.ofNullable(comment.getContent())
                .ifPresent(lastComment::setContent);

        return commentRepository.save(lastComment);
    }

    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception(ExceptionEnum.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
    }
}
