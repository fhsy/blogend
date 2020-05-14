package com.monggo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.monggo.common.type.ArticleParameterEnum;
import com.monggo.common.type.ArticleType;
import com.monggo.common.utils.R;
import com.monggo.entity.*;
import com.monggo.mapper.ArticleMapper;
import com.monggo.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monggo.service.IArticleTagService;
import com.monggo.service.ICategoryService;
import com.monggo.service.ITagsService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.MethodWrapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ITagsService tagsService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IArticleTagService articleTagService;


    @Override
    public R add(Article article) {
        if (article == null) {
            return R.error("数据为空");
        }
        if (StringUtils.isEmpty(article.getState())) {
            article.setState(ArticleType.DRAFT.getType());
        }
        if (!(ArticleType.DRAFT.getType() == article.getState() ||
                ArticleType.RECOVERY.getType() == article.getState() ||
                ArticleType.RELEASE.getType() == article.getState())) {
            article.setState(ArticleType.DRAFT.getType());
        }
        if (StringUtils.isEmpty(article.getTitle())) {
            article.setTitle(LocalDate.now().toString());
        }
        this.save(article);
        return R.ok("添加成功");
    }

    @Override
    public R list(String type) {
        List<Article> list = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        if (type != null && type == ArticleType.RELEASE.getType()) {
            queryWrapper.eq("state", ArticleType.RELEASE.getType());
            list = this.list(queryWrapper);
        }
        if (type != null && type == ArticleType.RECOVERY.getType()) {
            queryWrapper.eq("state", ArticleType.RECOVERY.getType());
            list = this.list(queryWrapper);
        }
        if (type != null && type == ArticleType.DRAFT.getType()) {
            queryWrapper.eq("state", ArticleType.DRAFT.getType());
            list = this.list(queryWrapper);
        }
        if (type == null || type != null
                && type != ArticleType.RELEASE.getType()
                && type != ArticleType.RECOVERY.getType()
                && type != ArticleType.DRAFT.getType()) {
            queryWrapper.like("state", ArticleType.RELEASE.getType());
            queryWrapper.or();
            queryWrapper.like("state", ArticleType.DRAFT.getType());
            list = this.list(queryWrapper);
        }
        List<IndexArticleVO> list2 = new ArrayList<>();
        for(Article item : list){
            Integer articleId = item.getArticleId();
            List<String> stringList = new ArrayList<>();
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("article_id", articleId);
            List<ArticleTag> articleTagList = articleTagService.list(queryWrapper2);
            for (ArticleTag item2 : articleTagList){
                QueryWrapper queryWrapper3 = new QueryWrapper();
                queryWrapper3.eq("tag_id", item2.getTagId());
                Tags one = tagsService.getOne(queryWrapper3);
                stringList.add(one.getTagName());
            }
            IndexArticleVO indexArticleVO = new IndexArticleVO();
            indexArticleVO.setArticleId(item.getArticleId());
            indexArticleVO.setCateId(item.getCateId());
            indexArticleVO.setCateName(item.getCateName());
            indexArticleVO.setContext(item.getContext());
            indexArticleVO.setCreateTime(item.getCreateTime());
            indexArticleVO.setState(item.getState());
            indexArticleVO.setTitle(item.getTitle());
            indexArticleVO.setUpdateTime(item.getUpdateTime());
            indexArticleVO.setTagNames(stringList);
            if(item.getCateId() != null){
                QueryWrapper queryWrapper3 = new QueryWrapper();
                queryWrapper3.eq("cate_id", item.getCateId());
                Category one = categoryService.getOne(queryWrapper3);
                indexArticleVO.setCateName(one.getCateName());
            }
            list2.add(indexArticleVO);
        }
        return R.ok().put("data", list2);
    }

    @Override
    public R indexList(Integer page, Integer size, String searchValue
            , Integer cateId
            , Integer tagId) {
        Page<IndexArticleVO> articlePage = new Page<>(page, size);
        List<IndexArticleVO> indexArticlePage = articleMapper.getIndexArticlePage(articlePage, searchValue, cateId, tagId);
        articlePage.setRecords(indexArticlePage);
        return R.ok().put("data", articlePage);
    }

    @Override
    public R cate_save(Integer articleId, String cateName) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("article_id", articleId);
        Article article = this.getOne(queryWrapper);

        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("cate_name", cateName);
        Category one = categoryService.getOne(queryWrapper2);
        if(one == null){
            Category category = new Category();
            category.setCateName(cateName);
            categoryService.save(category);
            article.setCateId(category.getCateId());
            this.saveOrUpdate(article);
        }
        if(one != null){
            article.setCateId(one.getCateId());
            this.saveOrUpdate(article);
        }
        return R.ok();
    }

    @Override
    public R get(Integer articleId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("article_id", articleId);
        Article item = this.getOne(queryWrapper);
        IndexArticleVO indexArticleVO = new IndexArticleVO();
        if(item == null){
            return R.error().put("msg", "文章不存在");
        }

            List<String> stringList = new ArrayList<>();
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("article_id", articleId);
            List<ArticleTag> articleTagList = articleTagService.list(queryWrapper2);
            for (ArticleTag item2 : articleTagList){
                QueryWrapper queryWrapper3 = new QueryWrapper();
                queryWrapper3.eq("tag_id", item2.getTagId());
                Tags tags = tagsService.getOne(queryWrapper3);
                stringList.add(tags.getTagName());
            }
            indexArticleVO.setArticleId(item.getArticleId());
            indexArticleVO.setCateId(item.getCateId());
            indexArticleVO.setCateName(item.getCateName());
            indexArticleVO.setContext(item.getContext());
            indexArticleVO.setCreateTime(item.getCreateTime());
            indexArticleVO.setState(item.getState());
            indexArticleVO.setTitle(item.getTitle());
            indexArticleVO.setUpdateTime(item.getUpdateTime());
            indexArticleVO.setTagNames(stringList);
            if(item.getCateId() != null){
                QueryWrapper queryWrapper3 = new QueryWrapper();
                queryWrapper3.eq("cate_id", item.getCateId());
                Category one = categoryService.getOne(queryWrapper3);
                indexArticleVO.setCateName(one.getCateName());
            }
        return R.ok().put("data", indexArticleVO);
    }
}
