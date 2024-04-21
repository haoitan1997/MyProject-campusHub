package com.haotian.campuslifehub.controller;

import com.haotian.campuslifehub.bean.Article;
import com.haotian.campuslifehub.bean.UserInfo;
import com.haotian.campuslifehub.common.Result;
import com.haotian.campuslifehub.service.ArticleService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    HttpSession session;
    @PostMapping("/article/fabiao")
    public Result fabiao(@RequestParam("files") MultipartFile[] files,
                         @RequestParam("text") String text, HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        //将前端发送的文件保存到本地磁盘
        for(int i = 0;i < files.length;i++){
            MultipartFile file = files[i];
            InputStream inputStream = file.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            ServletContext servletContext = request.getServletContext();
            String realPath = servletContext.getRealPath("/upload");
            File file1 = new File(realPath);
            if(!file1.exists()){
                file1.mkdir();
            }
            String originalFilename = file.getOriginalFilename();
            String reaAdd = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
            String picAddress = file1.getAbsolutePath() + '/' + reaAdd;
            File destPath = new File(picAddress);
            FileOutputStream fileOutputStream = new FileOutputStream(destPath);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] bytes = new byte[1024 * 10];
            int count = 0;
            while((count = bufferedInputStream.read(bytes)) != -1){
                bufferedOutputStream.write(bytes,0,count);
            }

            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            bufferedInputStream.close();
            if(i != files.length -1){
                sb.append(reaAdd + ",");
            }else{
                sb.append(reaAdd);
            }
        }
        String token = (String)session.getAttribute("userInfo:token");
        UserInfo userInfo = (UserInfo)session.getAttribute("user:"+token);
        Article article = new Article();
        article.setContent(text);
        article.setImage(sb.toString());
        article.setUserId(userInfo.getId());
        articleService.saveArticle(article);
        return new Result("200","成功",null);
    }
}
