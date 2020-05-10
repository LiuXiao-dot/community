package xyz.lsxwy.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.lsxwy.community.dto.CommentCreateDTO;
import xyz.lsxwy.community.dto.CommentDTO;
import xyz.lsxwy.community.dto.ResultDTO;
import xyz.lsxwy.community.enums.CommentTypeEnum;
import xyz.lsxwy.community.exception.CustomizeErrorCode;
import xyz.lsxwy.community.model.Comment;
import xyz.lsxwy.community.model.User;
import xyz.lsxwy.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>文件名: CommentController.java</p>
 * <p>描述: </p>
 *
 * @author 蓝色夏威夷
 * @version 1.0.0
 * @date 2020/5/5 15:47
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(comment.getGmtModified());
        comment.setCommentator(user.getAccountId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO comments(@PathVariable(name = "id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
