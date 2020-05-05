package xyz.lsxwy.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import xyz.lsxwy.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>文件名: ControllerAdvice.java</p>
 * <p>描述: </p>
 *
 * @author 蓝色夏威夷
 * @version 1.0.0
 * @date 2020/5/5 11:40
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model) {
        HttpStatus status = getStatus(request);

        if (e instanceof CustomizeException) {
            model.addAttribute("message", e.getMessage());
        } else
            model.addAttribute("message", 404);
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
