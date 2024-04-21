package com.haotian.campuslifehub.service.userServiceImp;

import com.haotian.campuslifehub.bean.Article;
import com.haotian.campuslifehub.bean.other.ArticleInfo;
import com.haotian.campuslifehub.mapper.ArticleMapper;
import com.haotian.campuslifehub.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public List<ArticleInfo> getArticles() {
        return articleMapper.getAllArticle();
    }

    @Override
    public void saveArticle(Article article) {
        articleMapper.saveArticle(article);
    }
}
