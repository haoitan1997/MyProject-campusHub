package com.haotian.campuslifehub.service;

import com.haotian.campuslifehub.bean.Article;
import com.haotian.campuslifehub.bean.other.ArticleInfo;

import java.util.List;

public interface ArticleService {
    List<ArticleInfo> getArticles();
    void saveArticle(Article article);
}
