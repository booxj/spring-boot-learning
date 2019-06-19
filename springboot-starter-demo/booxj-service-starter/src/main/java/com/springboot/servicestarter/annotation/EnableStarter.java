package com.springboot.servicestarter.annotation;

import com.springboot.servicestarter.configuration.ServiceConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 10:08
 * @since
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ServiceConfiguration.class})
public @interface EnableStarter {
}
