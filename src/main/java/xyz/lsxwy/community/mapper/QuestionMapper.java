package xyz.lsxwy.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.lsxwy.community.dto.QuestionDTO;
import xyz.lsxwy.community.model.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO QUESTION (title,description,gmt_create,gmt_modified,creator,tag) VALUES (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    public void create(Question question);

    @Select("SELECT * from QUESTION LIMIT #{offset},#{size}")
    List<Question> listAll(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM QUESTION")
    Integer count();

    @Select("SELECT * from QUESTION WHERE creator=#{creator} LIMIT #{offset},#{size}")
    List<Question> listByCreator(@Param(value = "creator") String creator, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM QUESTION WHERE creator=#{creator}")
    Integer countByCreator(@Param(value = "creator") String creator);

    @Select("SELECT * from QUESTION WHERE id=#{id}")
    Question getById(@Param("id") Integer id);
}
