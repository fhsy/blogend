package top.faig.blog.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.faig.blog.article.entity.ArticleTag;
import top.faig.blog.article.mapper.ArticleTagMapper;
import top.faig.blog.article.service.IArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.faig.blog.common.utils.R;
import top.faig.blog.tags.entity.Tags;
import top.faig.blog.tags.service.ITagsService;


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

    @Autowired
    ITagsService tagsService;


    @Override
    public R stick(Integer articleId, String tagNames) {
        if(articleId == null && articleId == 0){
            return R.error("id为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("article_id", articleId);
        this.remove(queryWrapper);
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(articleId);
        String[] split = tagNames.split(",");
        for (String item : split){
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("tag_name", item);
            Tags one = tagsService.getOne(queryWrapper1);
            if(one == null){
                Tags tags = new Tags();
                tags.setTagName(item);
                tagsService.save(tags);
                articleTag.setTagId(tags.getTagId());
                this.save(articleTag);
            }
            if(one != null){
                articleTag.setTagId(one.getTagId());
                this.save(articleTag);
            }
        }
        return R.ok();
    }
}
