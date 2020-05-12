package xyz.lsxwy.community.mapper;

import xyz.lsxwy.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment record);
}