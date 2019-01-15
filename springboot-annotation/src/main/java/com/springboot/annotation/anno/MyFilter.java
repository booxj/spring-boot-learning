package com.springboot.annotation.anno;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyFilter implements TypeFilter {

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        // 获取当前类注解信息(当前类指componentScan指定扫描的类,)
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        // 获取当前正在扫描的类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();

        // 获取当前类的资源(类的路径)
        Resource resource = metadataReader.getResource();

        // 获取类名
        String className = classMetadata.getClassName();

        if (className.contains("service")){
            return true;
        }
        return false;
    }
}
