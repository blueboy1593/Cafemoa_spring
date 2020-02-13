package com.latte.admin.domain.user;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//prefix = "file" 로 선언 된 부분은 application.properties 에 선언한
//file.upload-dir=.uploads
//file 필드에 접근한다는 말이다.
@Configuration
@ConfigurationProperties(prefix="file")
public class FileUploadProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}