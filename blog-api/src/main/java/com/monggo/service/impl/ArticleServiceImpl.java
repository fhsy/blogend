package com.monggo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
import org.springframework.beans.factory.annotation.Autowired;
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
    private ITagsService tagsService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IArticleTagService articleTagService;



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
            article.setTitle(LocalDate.now().toString());
        }
        this.save(article);
        return R.ok("添加成功");
    }
    @Override
    public R list(String type) {
        List list = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        if(type != null && type == ArticleType.RELEASE.getType()){
            queryWrapper.eq("state", ArticleType.RELEASE.getType());
            list = this.list(queryWrapper);
        }
        if(type != null && type == ArticleType.RECOVERY.getType()){
            queryWrapper.eq("state", ArticleType.RECOVERY.getType());
            list = this.list(queryWrapper);
        }
        if(type != null && type == ArticleType.DRAFT.getType()){
            queryWrapper.eq("state", ArticleType.DRAFT.getType());
            list = this.list(queryWrapper);
        }
        if(type == null || type != null
                && type != ArticleType.RELEASE.getType()
                && type != ArticleType.RECOVERY.getType()
                && type != ArticleType.DRAFT.getType() ){
            queryWrapper.like("state", ArticleType.RELEASE.getType());
            queryWrapper.or();
            queryWrapper.like("state", ArticleType.DRAFT.getType());
            list = this.list(queryWrapper);
        }
        return R.ok().put("list", list);
    }
    @Override
    public R indexList(Integer page, Integer size) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        IPage<Article> articleIPage = new Page<>(page, size);
        IPage<Article> page1 = this.page(articleIPage);
        return R.ok().put("data", page1);
//        // type 判断数据类型哪种方式返回  1时间顺序  2标签  3分类
//        if(type == null || type.equals(ArticleParameterEnum.DATE.getCode())){

            // this.isSeach(queryWrapper, val, valType);
//            List<Article> list = this.list(queryWrapper);
//            return R.ok().put("data", list);
//        }
//        if (type != null && type.equals(ArticleParameterEnum.TAGS.getCode())){ // 标签分类
//            List<IndexTagsArticle> indexArticleList = new ArrayList<>();
//            List<Tags> tagsList = tagsService.list(null);
//            // 遍历标签列表
//            for(Tags tags : tagsList){
//                Integer tagId = tags.getTagId();
//                //查询条件函数
//                QueryWrapper queryWrapper1 = new QueryWrapper();
//                queryWrapper1.eq("tag_id", tagId);
//                List<ArticleTag> articleTagList = articleTagService.list(queryWrapper1);
//                List<Article> articleList = new ArrayList<>();
//                // 遍历标签对应博文列表
//                for (ArticleTag articleTag : articleTagList){
//                    // 得到对应标签的博文id
//                    Integer articleId = articleTag.getArticleId();
//                    QueryWrapper queryWrapper = new QueryWrapper();
//                    queryWrapper.eq("article_id", articleId);
//                    this.isSeach(queryWrapper, val, valType);
//                    // 查询博文 添加到列表
//                    Article article = this.getOne(queryWrapper);
//                    if(article != null ){  // 存在不满足搜索条件
//                        articleList.add(article);
//                    }
//                }
//                // 创建实例 接收 标签和博文列表
//                IndexTagsArticle indexArticle = new IndexTagsArticle();
//                indexArticle.setTagId(tagId);
//                indexArticle.setTagName(tags.getTagName());
//                indexArticle.setArticleList(articleList);
//                indexArticleList.add(indexArticle);
//            }
//            return R.ok().put("data", indexArticleList);
//        }
//        if (type != null && type.equals(ArticleParameterEnum.CATEGORY.getCode())){ // 分类排
//            List<Category> categoryList = categoryService.list(null);
//            List<IndexCategoryArticle> indexArticleList = new ArrayList<>();
//            // 遍历分类列表
//            for(Category category : categoryList){
//                // 得到分类 ID
//                Integer cateId = category.getCateId();
//                QueryWrapper queryWrapper = new QueryWrapper();
//                // 搜索参数
//                queryWrapper.eq("cate_id", cateId);
//                this.isSeach(queryWrapper, val, valType);
//                List<Article> list1 = this.list(queryWrapper);
//                IndexCategoryArticle indexCategoryArticle = new IndexCategoryArticle();
//                indexCategoryArticle.setCateId(cateId);
//                indexCategoryArticle.setCateName(category.getCateName());
//                indexCategoryArticle.setArticleList(list1);
//                indexArticleList.add(indexCategoryArticle);
//            }
//            return R.ok().put("data", indexArticleList);
//        }
//        return R.error();
    }


    /**
     * 内部方法 判断是否要搜索
     * @param queryWrapper
     * @param val
     * @param valType
     * @return
     */
    private QueryWrapper isSeach(QueryWrapper queryWrapper, String val, Integer valType){
        // 查找发布状态下的
        queryWrapper.eq("state", ArticleType.RELEASE.getType());
        //是否搜索
        if(!StringUtils.isEmpty(val)){
            // 根据哪个字段搜
            if(valType != null && valType.equals(ArticleParameterEnum.TITLE.getCode())){
                queryWrapper.like("title", val);
            }
            if(valType != null && valType.equals(ArticleParameterEnum.CONTEXT.getCode())){
                queryWrapper.like("context", val);
            }
            // 不传搜索模式
            if(valType == null || valType != null &&
                    !valType.equals(ArticleParameterEnum.TITLE.getCode()) &&
                    !valType.equals(ArticleParameterEnum.CONTEXT.getCode())){
                queryWrapper.like("title", val);
            }
        }
        return queryWrapper;
    }
}
