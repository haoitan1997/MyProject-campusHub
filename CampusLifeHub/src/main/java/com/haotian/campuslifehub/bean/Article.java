package com.haotian.campuslifehub.bean;

import lombok.Data;

import java.util.Date;
@Data
public class Article {
    private Integer id;
    private Integer userId;
    private String content;
    private String image;
    private Date createTime;
    private String comments;
    private Integer dianzan;
}
