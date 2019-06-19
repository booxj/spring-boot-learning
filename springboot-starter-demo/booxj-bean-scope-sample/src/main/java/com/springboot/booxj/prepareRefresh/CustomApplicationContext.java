package com.springboot.booxj.prepareRefresh;

import com.springboot.booxj.BeanScopeSample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

/**
 * AbstractApplicationContext
 * prepareRefresh()->prepareRefresh()->initPropertySources()
 *
 * @author booxj
 * @create 2019/6/19 13:41
 * @since
 */
public class CustomApplicationContext extends AnnotationConfigServletWebServerApplicationContext {

    @Override
    protected void initPropertySources() {
        super.initPropertySources();
        //把"MYSQL_HOST"作为启动的时候必须验证的环境变量
        getEnvironment().setRequiredProperties("MYSQL_HOST");
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BeanScopeSample.class);
        springApplication.setApplicationContextClass(CustomApplicationContext.class);
        springApplication.run(args);
    }
}
