package com.monggo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monggo.common.type.ArticleParameterEnum;
import com.monggo.common.type.ArticleType;
import com.monggo.common.utils.R;
import com.monggo.entity.Article;
import com.monggo.entity.ArticleTag;
import com.monggo.entity.Tags;
import com.monggo.mapper.ArticleMapper;
import com.monggo.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monggo.service.IArticleTagService;
import com.monggo.service.ICategoryService;
import com.monggo.service.ITagsService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
    public R indexList(Integer type, String val, Integer valType) {
        List<Article> list = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        // 只要发布状态下的博文
        queryWrapper.eq("state", ArticleType.RELEASE.getType());
        //是否搜索
        if(!StringUtils.isEmpty(val)){
            // 根据哪个字段搜
            if(valType != null && valType.equals(ArticleParameterEnum.TITLE.getCode())){
                queryWrapper.eq("title", val);
            }
            if(valType != null && valType.equals(ArticleParameterEnum.CONTEXT.getCode())){
                queryWrapper.eq("context", val);
            }
            // 不传根据字段 就搜2种都要
            if(valType == null || valType != null &&
                    !valType.equals(ArticleParameterEnum.TITLE.getCode()) &&
                    !valType.equals(ArticleParameterEnum.CONTEXT.getCode())){
                queryWrapper.eq("context", val);
                queryWrapper.or();
                queryWrapper.eq("title", val);
            }
        }
        // type 判断数据类型哪种方式返回  1时间顺序  2标签  3分类
        if(type != null && type.equals(ArticleParameterEnum.DATE.getCode())){
            queryWrapper.orderByDesc("create_time");
            list = this.list(queryWrapper);
        }
        if (type != null && type.equals(ArticleParameterEnum.TAGS.getCode())){
            list = this.list(queryWrapper);
            Iterator iterator = list.iterator();
            List<Map> mapList = new ArrayList<>();
            HashMap hashMap = new HashMap();
            hashMap.put("无", null);
            mapList.add(hashMap);
            while (iterator.hasNext()){
                Article article = (Article) iterator.next();
                Integer articleId = article.getArticleId();
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.like("article_id", article.getArticleId());
                ArticleTag articleTag = articleTagService.getOne(queryWrapper1);
                if(articleTag == null){
                    Iterator<Map> iterator1 = mapList.iterator();


                }

            }

//            List<Tags> tagsList = tagsService.list();
//            Iterator<Tags> iterator = tagsList.iterator();
//            while (iterator.hasNext()){
//                Tags tags = iterator.next();
//                queryWrapper.eq("")
//                tags.getTagName();
//            }
        }
        return R.ok();
    }
}
