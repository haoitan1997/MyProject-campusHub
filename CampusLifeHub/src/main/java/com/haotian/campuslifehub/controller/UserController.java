package com.haotian.campuslifehub.controller;

import com.haotian.campuslifehub.bean.Article;
import com.haotian.campuslifehub.bean.Role;
import com.haotian.campuslifehub.bean.UserInfo;
import com.haotian.campuslifehub.bean.other.ArticleInfo;
import com.haotian.campuslifehub.bean.vo.DayLifeVo;
import com.haotian.campuslifehub.common.Result;
import com.haotian.campuslifehub.service.ArticleService;
import com.haotian.campuslifehub.service.UserService;
import com.haotian.campuslifehub.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    HttpSession session;


    @PostMapping("/user/register")
    public Result register(@RequestParam("username")String username,
                            @RequestParam("password")String password){
        String message = userService.register(username,password);
        Result result = new Result();
        result.setCode("200");
        result.setData(null);
        result.setMessage(message);
        return result;
    }

    @PostMapping("/user/loginAndGetRole")
    public Result login(@RequestParam("user")String username,
                        @RequestParam("pw")String password){
        boolean isFlag = userService.login(username,password);
        Result result = new Result();
        if(isFlag){
            result.setCode("200");
            result.setData(null);
            result.setMessage("登录成功");
        }else{
            result.setCode("201");
            result.setData(null);
            result.setMessage("用户名或密码错误");
        }
        return result;
    }

    @GetMapping("/user/dayLife")
    public Result dayLife(){
        String token = (String)session.getAttribute("userInfo:token");
        UserInfo userInfo = (UserInfo)session.getAttribute("user:"+token);
        List<ArticleInfo> articles = articleService.getArticles();
        Role role = userService.getRole(userInfo.getRoleId());
        DayLifeVo dayLifeVo = new DayLifeVo();
        dayLifeVo.setUserInfo(userInfo);
        dayLifeVo.setArticleInfos(articles);
        dayLifeVo.setRole(role);
        Result result = new Result();
        result.setMessage("请求成功");
        result.setData(dayLifeVo);
        result.setCode("200");
        return result;
    }

    @PostMapping("/user/submitUserInfo")
    public Result submitUserInfo(@RequestParam(name = "sex") String sex,
                                 @RequestParam(name = "isAlone") String isAlone,
                                 @RequestParam(name = "age") String age){
        String token = (String)session.getAttribute("userInfo:token");
        UserInfo userInfo = (UserInfo)session.getAttribute("user:"+token);
        if(sex.equals("man")){
            userInfo.setSex(1);
        }else if(sex.equals("women")){
            userInfo.setSex(0);
        }
        if(isAlone.equals("alone")){
            userInfo.setAlone(1);
        }else if(isAlone.equals("notAlone")){
            userInfo.setAlone(0);
        }
        if(age != ""){
            userInfo.setAge(Integer.parseInt(age));
        }
        userService.saveUserInfo(userInfo);
        return new Result("200","成功",null);
    }

}
