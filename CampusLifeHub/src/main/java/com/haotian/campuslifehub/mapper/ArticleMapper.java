package com.haotian.campuslifehub.mapper;

import com.haotian.campuslifehub.bean.Article;
import com.haotian.campuslifehub.bean.other.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<ArticleInfo> getAllArticle();
    void saveArticle(Article article);
}
