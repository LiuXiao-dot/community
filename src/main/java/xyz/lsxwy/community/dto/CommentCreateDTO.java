package xyz.lsxwy.community.dto;

import lombok.Data;

/**
 * <p>文件名: CommentDTO.java</p>
 * <p>描述: </p>
 * @author 蓝色夏威夷
 * @date 2020/5/5 15:50
 * @version 1.0.0
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
