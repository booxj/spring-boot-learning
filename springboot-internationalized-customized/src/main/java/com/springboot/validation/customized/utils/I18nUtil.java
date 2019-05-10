package com.springboot.validation.customized.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * @ClassName: I18nUtil.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/9 18:29
 * @version:
 */
@Component
public class I18nUtil {

    @Autowired
    private MessageSource messageSource;

    private static MessageSource i18nSource;

    @PostConstruct
    private void init() {
        i18nSource = messageSource;
    }

    /**
     * @param code 对应文本配置的key
     * @return 对应地区的语言消息字符串
     */
    public static String getMessage(String code) {
        return getMessage(code, new Object[]{});
    }

    public static String getMessage(String code, String defaultMessage) {
        return getMessage(code, null, defaultMessage);
    }

    public static String getMessage(String code, String defaultMessage, Locale locale) {
        return getMessage(code, null, defaultMessage, locale);
    }

    public static String getMessage(String code, Locale locale) {
        return getMessage(code, null, "", locale);
    }

    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        return getMessage(code, args, "", locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(code, args, defaultMessage, locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return i18nSource.getMessage(code, args, defaultMessage, locale);
    }

    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
