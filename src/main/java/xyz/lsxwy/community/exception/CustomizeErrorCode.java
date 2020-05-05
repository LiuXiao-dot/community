package xyz.lsxwy.community.exception;
/**
 * <p>文件名: CustomizeErrorCode.java</p>
 * <p>描述: </p>
 * @author 蓝色夏威夷
 * @date 2020/5/5 12:06
 * @version 1.0.0
 */
public enum  CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND("您找的问题不在了");
    private String message;

    @Override
    public String getMessage(){
        return message;
    }

    CustomizeErrorCode(String message)
    {
        this.message = message;
    }

}
