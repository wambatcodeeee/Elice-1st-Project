package com.example.firstproject.board.entity;

import com.example.firstproject.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "board_title")
    private String boardTitle;

    @Column
    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public void update(Long id){
        this.id = id;
    }

    @Builder(toBuilder = true)
    public Board(Long id, String boardTitle, String content) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.content = content;
    }
}
