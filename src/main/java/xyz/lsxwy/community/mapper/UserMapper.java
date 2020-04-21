package xyz.lsxwy.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.lsxwy.community.model.User;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER (account_id, name, token, gmt_create, gmt_modified) VALUES ( #{accountId}, #{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("SELECT * FROM USER WHERE token = #{token}")
    User findByToken(@Param("token") String token);
}
