package com.mh.blog.handler;

import com.sun.xml.bind.v2.schemagen.xmlschema.Annotated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /*该注解就是表示这个方法可以做异常处理的，即拦击所有的Exception，加上该注解才有效果 */
    @ExceptionHandler(Exception.class)
                                        /*HttpServletRequest request :该参数可获取异常路径的url*/
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        /*记录异常信息*/
        logger.error("Requst URL: {},Exception : {}",request.getRequestURL(),e);
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        /*设置上述异常信息打印的路径*/
        mv.setViewName("error/error");
        return mv;
    }
}
