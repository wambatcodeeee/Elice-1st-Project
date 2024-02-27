package com.example.firstproject.post.entity;

import com.example.firstproject.BaseEntity;
import com.example.firstproject.board.entity.Board;
import com.example.firstproject.comment.entity.Comment;
import com.example.firstproject.image.entity.Image;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "post")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private Image image;

    public void setBoard(Board board) {
        this.board = board;
        if (!this.board.getPosts().contains(this)) {
            this.board.getPosts().add(this);
        }
    }
}
