package com.example.firstproject.image.entity;

import com.example.firstproject.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String url;

    @OneToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Post post;
}
