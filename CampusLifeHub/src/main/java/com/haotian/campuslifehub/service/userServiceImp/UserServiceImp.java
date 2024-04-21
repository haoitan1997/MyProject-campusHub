package com.haotian.campuslifehub.service.userServiceImp;

import com.haotian.campuslifehub.bean.Role;
import com.haotian.campuslifehub.bean.UserInfo;
import com.haotian.campuslifehub.mapper.ArticleMapper;
import com.haotian.campuslifehub.mapper.UserMapper;
import com.haotian.campuslifehub.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    HttpSession session;


    /**
     * 用户注册功能
     * @param username
     * @param password
     * @return
     */
    @Override
    @Transactional
    public String register(String username, String password) {
        //1、根据用户名查询数据库是否存在该用户
        UserInfo user = userMapper.selectByName(username);
        //存在则提示用户已存在
        if(user != null && user.getUsername().equals(username)){
            return "用户名已存在";
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        //2、根据用户信息存入数据库
        userMapper.addUser(userInfo);
        return "注册成功";

    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean login(String username, String password) {
        //1、根据用户名查询是否正确
        UserInfo userInfo = userMapper.selectByName(username);
        if(userInfo == null){
            return false;
        }
        //2、若用户名正确，查询密码是否正确
        if(!userInfo.getPassword().equals(password)){
            return false;
        }
        if(userInfo.getRoleId() == null){
            long count = userMapper.getRoleCount();
            int roleIndex = (int)(Math.random() * count) + 1;
            userInfo.setRoleId(roleIndex);
            HashMap map = new HashMap<>();
            map.put("roleId",roleIndex);
            map.put("username",username);
            userMapper.saveRole(map);
        }
        String token = UUID.randomUUID().toString().replace("-","");
        session.setAttribute("userInfo:token",token);
        session.setAttribute("user:"+token,userInfo);
        return true;
    }

    /**
     * 根据用户id查询所属角色
     * @param id
     * @return
     */
    @Override
    public Role getRole(Integer id) {
        Role role = userMapper.selectRoleById(id);
        return role;
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        userMapper.saveUserInfo(userInfo);
    }


}
