package com.springboot.booxj.configuration;

import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 11:03
 * @since
 */
public class ImportSelector implements org.springframework.context.annotation.ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("selectImports : " + this.getClass().getSimpleName());
        return new String[]{"com.springboot.booxj.service.impl.ImportService2Impl"};
    }
}
