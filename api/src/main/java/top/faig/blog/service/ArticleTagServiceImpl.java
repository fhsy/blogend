package top.faig.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.faig.blog.code.entity.ArticleTag;
import top.faig.blog.code.entity.Tags;
import top.faig.blog.code.mapper.ArticleTagMapper;
import top.faig.blog.code.mapper.TagsMapper;
import top.faig.blog.common.utils.R;


/**
 * <p>
 * 博文标签表 服务实现类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@Service
@RequiredArgsConstructor
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> {


    private final TagsMapper tagsMapper;


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
            Tags one = tagsMapper.selectOne(queryWrapper1);
            if(one == null){
                Tags tags = new Tags();
                tags.setTagName(item);
                tagsMapper.insert(tags);
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
