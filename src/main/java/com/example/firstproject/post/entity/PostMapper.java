package com.example.firstproject.post.entity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface PostMapper {
    Post postDTOToPost(PostDTO postDTO);
}
