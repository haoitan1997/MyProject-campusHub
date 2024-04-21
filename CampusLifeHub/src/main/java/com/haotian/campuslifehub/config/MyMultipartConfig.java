package com.haotian.campuslifehub.config;

import jakarta.servlet.annotation.MultipartConfig;

import java.lang.annotation.Annotation;

public class MyMultipartConfig implements MultipartConfig {
    @Override
    public String location() {
        return null;
    }

    @Override
    public long maxFileSize() {
        return 1240000;
    }

    @Override
    public long maxRequestSize() {
        return 1240000;
    }

    @Override
    public int fileSizeThreshold() {
        return 0;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
