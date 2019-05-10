package com.springboot.validation.customized.config.messagesource;

import com.springboot.validation.customized.annotation.I18n;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: MessageResourceInterceptor.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/10 10:27
 * @version:
 */
public class MessageResourceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在跳转到该方法先清除request中的国际化信息
        request.removeAttribute(MessageResourceProperties.I18N_ATTRIBUTE);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        // 在方法中设置i18路径
        if (null != request.getAttribute(MessageResourceProperties.I18N_ATTRIBUTE)) {
            return;
        }

        //判断是否HandlerMethod
        HandlerMethod method = null;
        if (handler instanceof HandlerMethod) {
            method = (HandlerMethod) handler;
        }
        if (null == method) {
            return;
        }

        // 在method注解了i18
        I18n i18nMethod = method.getMethodAnnotation(I18n.class);
        if (null != i18nMethod) {
            request.setAttribute(MessageResourceProperties.I18N_ATTRIBUTE, i18nMethod.value());
            return;
        }

        // 在Controller上注解了i18
        I18n i18nController = method.getBeanType().getAnnotation(I18n.class);
        if (null != i18nController) {
            request.setAttribute(MessageResourceProperties.I18N_ATTRIBUTE, i18nController.value());
            return;
        }

        // 根据Controller名字设置i18
        String controller = method.getBeanType().getName();
        int index = controller.lastIndexOf(".");
        if (index != -1) {
            controller = controller.substring(index + 1, controller.length());
        }
        index = controller.toUpperCase().indexOf("CONTROLLER");
        if (index != -1) {
            controller = controller.substring(0, index);
        }
        request.setAttribute(MessageResourceProperties.I18N_ATTRIBUTE, controller);
    }
}
