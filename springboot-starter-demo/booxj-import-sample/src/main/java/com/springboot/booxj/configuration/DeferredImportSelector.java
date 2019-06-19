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
public class DeferredImportSelector implements org.springframework.context.annotation.DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("deferredImportSelector : " + this.getClass().getSimpleName());
        return new String[]{"com.springboot.booxj.service.impl.ImportService3Impl"};
    }
}
