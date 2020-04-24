package xyz.lsxwy.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.lsxwy.community.dto.PaginationDTO;
import xyz.lsxwy.community.dto.QuestionDTO;
import xyz.lsxwy.community.mapper.QuestionMapper;
import xyz.lsxwy.community.mapper.UserMapper;
import xyz.lsxwy.community.model.Question;
import xyz.lsxwy.community.model.User;
import xyz.lsxwy.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        /**
         * <p>方法名: index</p>
         * <p>
         *     描述: 主页面；获取cookies，若token存在，则将token中的user写入到session中。
         *     如果cookies不存在，则不会有session信息，index界面将显示登录按键。
         * </p>
         * @param request
         */
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        PaginationDTO pagination = questionService.list(page,size);

        model.addAttribute("pagination", pagination);
        return "index";
    }
}
