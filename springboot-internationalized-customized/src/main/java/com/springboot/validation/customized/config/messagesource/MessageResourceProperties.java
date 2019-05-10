package com.springboot.validation.customized.config.messagesource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MessageResourceProperties.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/10 13:33
 * @version:
 */
@Component
@ConfigurationProperties(prefix = "spring.messages")
public class MessageResourceProperties {

    public static String I18N_ATTRIBUTE = "i18n_attribute";

    /**
     * 指定的国际化文件目录
     */
    String basename;
    /**
     * 父MessageSource指定的国际化文件
     */
    String baseFolder = "i18n";

    public String getBasename() {
        return basename;
    }

    public void setBasename(String basename) {
        this.basename = basename;
    }

    public String getBaseFolder() {
        return baseFolder;
    }

    public void setBaseFolder(String baseFolder) {
        this.baseFolder = baseFolder;
    }

}
