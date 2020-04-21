package xyz.lsxwy.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.lsxwy.community.mapper.UserMapper;
import xyz.lsxwy.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        /**
         * <p>方法名: index</p>
         * <p>
         *     描述: 主页面；获取cookies，若token存在，则将token中的user写入到session中。
         *     如果cookies不存在，则不会有session信息，index界面将显示登录按键。
         * </p>
         * @param request
         */
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
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
        return "index";
    }
}
