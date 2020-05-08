package xyz.lsxwy.community.exception;

/**
 * <p>文件名: CustomizeErrorCode.java</p>
 * <p>描述: </p>
 *
 * @author 蓝色夏威夷
 * @version 1.0.0
 * @date 2020/5/5 12:06
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"您找的问题不在了"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请先登录再进行操作"),
    SYS_ERROR(2004,"服务器冒烟了，请稍后再试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"您所回复的评论不存在了");

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
