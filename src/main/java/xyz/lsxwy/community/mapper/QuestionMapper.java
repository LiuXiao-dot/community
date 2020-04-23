package xyz.lsxwy.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import xyz.lsxwy.community.model.Question;

@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO QUESTION (title,description,gmt_create,gmt_modified,creator,tag) VALUES (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);
}
