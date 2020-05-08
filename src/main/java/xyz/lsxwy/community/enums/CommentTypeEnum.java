package xyz.lsxwy.community.enums;

import xyz.lsxwy.community.model.Comment;

/**
 * <p>文件名: CommentTypeEnum.java</p>
 * <p>描述: </p>
 *
 * @author 蓝色夏威夷
 * @version 1.0.0
 * @date 2020/5/7 12:30
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type)
    {
        for(CommentTypeEnum commentTypeEnum:CommentTypeEnum.values())
        {
            if (commentTypeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }
}
