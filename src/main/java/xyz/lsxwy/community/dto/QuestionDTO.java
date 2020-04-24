package xyz.lsxwy.community.dto;

import lombok.Data;
import xyz.lsxwy.community.model.User;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmt_create;
    private Long gmt_modified;
    private String creator;
    private Integer view_count;
    private Integer comment_count;
    private Integer like_count;
    private User user;
}
