package com.haotian.campuslifehub.bean.vo;

import com.haotian.campuslifehub.bean.Article;
import com.haotian.campuslifehub.bean.Role;
import com.haotian.campuslifehub.bean.UserInfo;
import com.haotian.campuslifehub.bean.other.ArticleInfo;
import lombok.Data;

import java.util.List;
@Data
public class DayLifeVo {
    private List<ArticleInfo> articleInfos;
    private UserInfo userInfo;
    private Role role;
}
