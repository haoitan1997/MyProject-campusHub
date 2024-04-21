package com.haotian.campuslifehub.service;

import com.haotian.campuslifehub.bean.Role;
import com.haotian.campuslifehub.bean.UserInfo;

public interface UserService {
    public String register(String username,String password);

    boolean login(String username, String password);
    Role getRole(Integer id);
    void saveUserInfo(UserInfo userInfo);
}
