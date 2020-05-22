package top.faig.blog.article.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.faig.blog.code.article.entity.Article;
import top.faig.blog.code.article.entity.Category;
import top.faig.blog.code.article.mapper.ArticleMapper;
import top.faig.blog.code.article.mapper.CategoryMapper;
import top.faig.blog.common.type.ArticleType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 类别表 服务实现类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@Service
@RequiredArgsConstructor
public class CategoryService extends ServiceImpl<CategoryMapper, Category> {

    private final ArticleMapper articleMapper;

    public void add(String name) {
        Category category = this.getOne(Wrappers.<Category>lambdaQuery()
                .eq(Category::getCateName, name));
        Optional.ofNullable(category)
                .filter(cate -> cate == null)
                .map(cate -> {
                    this.save(new Category().setCateName(cate.getCateName()));
                    return cate;
                });
    }

    public List<Category> classCount() {
        List<Article> articleList = articleMapper.selectList(Wrappers.<Article>lambdaQuery()
                .eq(Article::getState, ArticleType.RELEASE.getType()));
        List<Category> categoryList = this.list();
        categoryList.stream().forEach(category -> {
            articleList.stream().forEach(article -> {
                Optional.ofNullable(article)
                        .filter(art -> art.getCateId() != null && art.getCateId().equals(category.getCateId()))
                        .map(art -> {
                            Optional.ofNullable(art)
                                    .filter(a -> category.getCount() == null)
                                    .map(a -> {
                                        category.setCount(1);
                                        return art;
                                    })
                                    .orElseGet(() -> {
                                        category.setCount(category.getCount() + 1);
                                        return art;
                                    });
                            return art;
                        });

            });
        });
        Collections.sort(categoryList, (o1, o2) -> {
            Optional.ofNullable(o1)
                    .filter(o -> o.getCount() == null)
                    .map(o -> {
                        o1.setCount(0);
                        return o;
                    });
            Optional.ofNullable(o2)
                    .filter(o -> o.getCount() == null)
                    .map(o -> {
                        o2.setCount(0);
                        return o;
                    });
            return o2.getCount() - o1.getCount();
        });
        return categoryList;
    }

}
