package com.example.firstproject.post.service;

import com.example.firstproject.Exception.Exception;
import com.example.firstproject.Exception.ExceptionEnum;
import com.example.firstproject.board.entity.Board;
import com.example.firstproject.board.service.BoardService;
import com.example.firstproject.post.entity.Post;
import com.example.firstproject.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final BoardService boardService;

    public PostService(PostRepository postRepository, BoardService boardService) {
        this.boardService = boardService;
        this.postRepository = postRepository;
    }

    public Page<Post> findPostsByBoardAndKeyword(Board board, String keyword, PageRequest pageRequest) {
        if (keyword != null && !keyword.isEmpty()) {
            return postRepository.findAllByBoardAndTitleContaining(board, keyword, pageRequest);
        } else {
            return postRepository.findAllByBoardOrderByTitleAsc(board, pageRequest);
        }
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new Exception(ExceptionEnum.POST_NOT_FOUND));
    }

    public Post createPost(Post post, Long boardId) {
        Board boardToCreate = boardService.findBoardById(boardId);
        post.setBoard(boardToCreate);
        return postRepository.save(post);
    }

    public Post updatePost(Post post, Long postId) {
        post.setId(postId);
        Post foundPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new Exception(ExceptionEnum.POST_NOT_FOUND));

        Optional.ofNullable(post.getTitle())
                .ifPresent(foundPost::setTitle);
        Optional.ofNullable(post.getContent())
                .ifPresent(foundPost::setContent);

        return postRepository.save(foundPost);
    }

    public void deletePost(Long id) {
        Post foundPost = postRepository.findById(id)
                .orElseThrow(() -> new Exception(ExceptionEnum.POST_NOT_FOUND));
        postRepository.delete(foundPost);
    }
}