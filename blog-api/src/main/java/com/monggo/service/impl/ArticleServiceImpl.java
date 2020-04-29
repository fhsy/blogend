package com.monggo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monggo.common.type.ArticleType;
import com.monggo.common.utils.R;
import com.monggo.entity.Article;
import com.monggo.mapper.ArticleMapper;
import com.monggo.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 博文表 服务实现类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public R add(Article article) {
        if(article == null){
            return R.error("数据为空");
        }
        if(StringUtils.isEmpty(article.getState())){
            article.setState(ArticleType.DRAFT.getType());
        }
        if(!(ArticleType.DRAFT.getType() == article.getState() ||
                ArticleType.RECOVERY.getType() == article.getState() ||
                ArticleType.RELEASE.getType() == article.getState())){
            article.setState(ArticleType.DRAFT.getType());
        }
        if(StringUtils.isEmpty(article.getTitle())){
            article.setTitle(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
        }
        this.save(article);
        return R.ok("添加成功");
    }


    @Override
    public R search(String val, Integer type) {
        List titleList = null;
        List contextList = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        if(type != null && type == 1){
            queryWrapper.like("title", val);
            titleList = this.list(queryWrapper);
        }
        if (type != null && type == 2){
            queryWrapper.like("context", val);
            contextList = this.list(queryWrapper);
        }
        if(type == null || type != null && type != 1 && type != 2){
            queryWrapper.like("title", val);
            titleList = this.list(queryWrapper);
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.like("context", val);
            contextList = this.list(queryWrapper2);
        }
        return R.ok().put("titleList", titleList).put("contextList",contextList);
    }

    @Override
    public R list(Integer type) {
        List list = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        //1： 发布 2： 删除 3：草稿
        if(type != null && type == 1){
            queryWrapper.eq("state", ArticleType.RELEASE.getType());
            list = this.list(queryWrapper);
        }
        if(type != null && type == 2){
            queryWrapper.eq("state", ArticleType.RECOVERY.getType());
            list = this.list(queryWrapper);
        }
        if(type != null && type == 3){
            queryWrapper.eq("state", ArticleType.DRAFT.getType());
            list = this.list(queryWrapper);
        }
        if(type == null || type != null && type != 1 && type != 2 && type != 3){
            queryWrapper.like("state", ArticleType.RELEASE.getType());
            queryWrapper.or();
            queryWrapper.like("state", ArticleType.DRAFT.getType());
            list = this.list(queryWrapper);
        }
        return R.ok().put("list", list);
    }
}
