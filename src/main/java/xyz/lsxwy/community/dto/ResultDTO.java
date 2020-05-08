package xyz.lsxwy.community.dto;

import lombok.Data;
import xyz.lsxwy.community.exception.CustomizeErrorCode;
import xyz.lsxwy.community.exception.CustomizeException;

/**
 * <p>文件名: ResponseDTO.java</p>
 * <p>描述: </p>
 * @author 蓝色夏威夷
 * @date 2020/5/7 12:25
 * @version 1.0.0
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String message)
    {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }
    public static ResultDTO errorOf(CustomizeErrorCode errorCode)
    {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultDTO okOf()
    {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }
}
