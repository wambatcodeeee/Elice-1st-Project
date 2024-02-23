package com.example.firstproject.board.repository;

import com.example.firstproject.board.entity.Board;
import com.example.firstproject.board.repository.BoardRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class BoardRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BoardRepositoryImpl boardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        Board board = new Board(1L, "Test Board");
        boardRepository.save(board);
        verify(jdbcTemplate, times(1)).update(eq("INSERT INTO Board (boardTitle) VALUES (?)"), eq("Test Board"));
    }

    @Test
    public void testUpdate() {
        Board board = new Board(1L, "Updated Board Title");
        boardRepository.update(board);
        verify(jdbcTemplate, times(1)).update(eq("UPDATE Board SET boardTitle = ? WHERE boardId = ?"), eq("Updated Board Title"), eq(1L));
    }

    @Test
    public void testDeleteById() {
        boardRepository.deleteById(1L);
        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM Board WHERE boardId = ?"), eq(1L));
    }
}

