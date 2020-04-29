package com.monggo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monggo.common.utils.R;
import com.monggo.entity.ArticleTag;
import com.monggo.mapper.ArticleTagMapper;
import com.monggo.service.IArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public R stick(ArticleTag articleTag) {
        if(articleTag != null && articleTag.getArticleId() == null || articleTag.getTagId() == null){
            return R.error("id为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("article_id", articleTag.getArticleId());
        queryWrapper.eq("tag_id", articleTag.getTagId());
        if(this.getOne(queryWrapper) != null){
            this.remove(queryWrapper);
            return R.ok("取消成功");
        } else {
            this.save(articleTag);
            return R.ok("添加成功");
        }
    }
}
