package xyz.lsxwy.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.lsxwy.community.dto.AccessTokenDTO;
import xyz.lsxwy.community.dto.GithubUser;
import xyz.lsxwy.community.mapper.UserMapper;
import xyz.lsxwy.community.model.User;
import xyz.lsxwy.community.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    /**描述: @Autowired可以自动装载对象，将get\set方法省略*/
    @Autowired
    private GithubProvider githubProvider;
    /**描述: @Value将括号中的值赋值给对象*/
    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        /**
         * <p>方法名: callback</p>
         * <p>描述: @GetMapping:用于get请求。@RequestParam：将请求参数绑定到控制器的方法参数上。
         * 响应方法。获取AccessToken，再得到AccessToken中的User信息。
         * 将用户数据插入到数据库中。
         * 写Cookie。
         * 重定向回index界面。
         * </p>
         * @param code
         * @param state
         * @param response
         */
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getLogin());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());

            userMapper.insert(user);

            //登录成功，写cookie 和 session
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(3 * 3600 * 24);
            response.addCookie(cookie);
//            request.getSession().setAttribute("user", githubUser);
        } else {
            //登录失败，重新登录
        }
        return "redirect:/";//重定向回index界面
    }

}
