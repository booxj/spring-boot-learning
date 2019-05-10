package com.springboot.validation.customized.config;

import com.springboot.validation.customized.config.messagesource.MessageResourceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * @ClassName: LocaleConfig.java
 * @Description: 配置国际化语言
 * @Author: booxj
 * @CreateDate 2019/5/9 18:33
 * @version:
 */
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieMaxAge(3600);
        localeResolver.setCookieName("Language");//设置存储的Cookie的name为Language
        return localeResolver;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");
        registry.addInterceptor(new LocaleChangeInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new MessageResourceInterceptor()).addPathPatterns("/**");
    }
}
