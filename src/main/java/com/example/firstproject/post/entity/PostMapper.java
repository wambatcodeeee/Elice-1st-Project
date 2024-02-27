package com.example.firstproject.post.entity;

import com.example.firstproject.post.dto.PostRequestDTO;

@org.mapstruct.Mapper(componentModel = "spring")
public interface PostMapper {
    Post postRequestDTOToPost(PostRequestDTO postRequestDTO);
}
