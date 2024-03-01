package com.example.firstproject.comment.entity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentDTOToComment(CommentDTO commentDTO);
}
