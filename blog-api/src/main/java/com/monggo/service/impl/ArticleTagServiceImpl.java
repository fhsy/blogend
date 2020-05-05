package com.monggo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monggo.common.utils.R;
import com.monggo.entity.ArticleTag;
import com.monggo.mapper.ArticleTagMapper;
import com.monggo.service.IArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 博文标签表 服务实现类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements IArticleTagService {


    @Override
    public R stick(Integer articleId, String tagIds) {
        if(articleId == null && articleId == 0){
            return R.error("id为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("article_id", articleId);
        // 删除其他标签
        this.remove(queryWrapper);
        if(!StringUtils.isEmpty(tagIds)){
            String[] split = tagIds.split(",");
            for (String tagId : split){
                ArticleTag articleTag = new ArticleTag()
                        .setArticleId(articleId)
                        .setTagId(Integer.parseInt(tagId));
                this.save(articleTag);
            }
        }
        return R.ok();
    }
}
