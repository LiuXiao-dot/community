package xyz.lsxwy.community.dto;

import lombok.Data;
import xyz.lsxwy.community.model.User;

@Data
public class CommentDTO {

        private Long id;
        private Long parentId;
        private Integer type;
        private String commentator;
        private Long gmtCreate;
        private Long gmtModified;
        private Long likeCount;
        private String content;
        private User user;
        private Integer commentCount;
}
