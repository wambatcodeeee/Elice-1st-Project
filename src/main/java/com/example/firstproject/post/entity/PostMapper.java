package com.example.firstproject.post.entity;

import com.example.firstproject.post.dto.PostRequestDTO;
import com.example.firstproject.post.dto.PostResponseDTO;

@org.mapstruct.Mapper(componentModel = "spring")
public interface PostMapper {
    Post postRequestDTOToPost(PostRequestDTO postRequestDTO);
    PostResponseDTO postToPostResponseDTO(Post post);
}
