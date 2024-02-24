package com.example.firstproject.post.service;

import com.example.firstproject.board.service.BoardService;
import com.example.firstproject.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final BoardService boardService;

    public PostService(PostRepository postRepository, BoardService boardService){
        this.postRepository = postRepository;
        this.boardService = boardService;
    }
}
