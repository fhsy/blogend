package top.faig.blog.article.service;


import top.faig.blog.article.entity.ArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;
import top.faig.blog.common.utils.R;

/**
 * <p>
 * 博文标签表 服务类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
public interface IArticleTagService extends IService<ArticleTag> {


    R stick(Integer articleId, String tagNames);
}
