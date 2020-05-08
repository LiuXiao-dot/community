package xyz.lsxwy.community.mapper;

import xyz.lsxwy.community.model.Question;

public interface QuestionExtMapper {
    int incView(Question record);
    int replace(Question record);
    int incCommentCount(Question record);
}