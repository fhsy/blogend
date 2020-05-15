package top.faig.blog.article.service;



import top.faig.blog.article.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import top.faig.blog.common.utils.R;

/**
 * <p>
 * 博文表 服务类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
public interface IArticleService extends IService<Article> {

    R add(Article article);

    R list(String type);

    R indexList(Integer page, Integer size, String searchValue
            , Integer cateId
            , Integer tagId);

    R cate_save(Integer articleId, String  cateName);

    R get(Integer articleId);

}
