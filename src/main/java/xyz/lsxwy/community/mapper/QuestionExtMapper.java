package xyz.lsxwy.community.mapper;

import xyz.lsxwy.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int replace(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);
}