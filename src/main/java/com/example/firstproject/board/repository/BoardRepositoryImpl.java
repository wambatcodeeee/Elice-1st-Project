package com.example.firstproject.board.repository;

import com.example.firstproject.board.entity.Board;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BoardRepositoryImpl implements BoardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Board board) {
        jdbcTemplate.update("INSERT INTO board (board_title, content, filename, filepath, user_id, password) VALUES (?, ?, ?, ?, ?, ?)",
                board.getBoardTitle(),
                board.getContent(),
                board.getFilename(),
                board.getFilepath(),
                board.getUser());
    }


    @Override
    public void update(Board board) {
        jdbcTemplate.update("UPDATE board SET board_title = ?, content = ?, filename = ?, filepath = ?, user_id = ?, password = ? WHERE id = ?",
                board.getBoardTitle(),
                board.getContent(),
                board.getFilename(),
                board.getFilepath(),
                board.getUser(),
                board.getId());
    }

    @Override
    public void deleteById(Long boardId) {
        jdbcTemplate.update("DELETE FROM board WHERE id = ?", boardId);
    }

    @Override
    public Board findById(Long boardId) {
        List<Board> result = jdbcTemplate.query("SELECT * FROM board WHERE id = ?",
                new Object[]{boardId},
                new BeanPropertyRowMapper<>(Board.class));

        return result.isEmpty() ? null : result.get(0);
    }


    @Override
    public List<Board> findAll() {
        return jdbcTemplate.query("SELECT * FROM board",
                new BeanPropertyRowMapper<>(Board.class));
    }
}
