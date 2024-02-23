package com.example.firstproject.board.entity;

import com.example.firstproject.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardId")
    private Long boardId;

    @Column(name = "boardTitle")
    private String boardTitle;

    @Column
    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public Board(long boardId, String boardTitle) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
    }
}
