package com.haotian.campuslifehub.bean;

import lombok.Data;

import java.util.Date;
@Data
public class UserInfo {
    private Integer id;
    private Integer roleId;
    private String username;
    private String password;
    private Integer sex;
    private Integer age;
    private Integer alone;
    private Date createTime;
}
