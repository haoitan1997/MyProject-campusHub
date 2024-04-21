package com.haotian.campuslifehub.mapper;

import com.haotian.campuslifehub.bean.Role;
import com.haotian.campuslifehub.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    public int addUser(UserInfo userInfo);
    public void saveUserInfo(UserInfo userInfo);

    UserInfo selectByName(String username);
    long getRoleCount();

    void saveRole(Map map);
    Role selectRoleById(Integer id);
}
