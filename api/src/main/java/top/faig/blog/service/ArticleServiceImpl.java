package top.faig.blog.service;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.faig.blog.code.entity.*;
import top.faig.blog.code.mapper.ArticleMapper;
import top.faig.blog.code.mapper.CategoryMapper;
import top.faig.blog.code.vo.ArticleVO;
import top.faig.blog.common.type.ArticleType;
import top.faig.blog.common.utils.R;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 博文表 服务实现类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> {

    private final ArticleMapper articleMapper;
    private final CategoryMapper categoryMapper;

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

    public R list(String type) {
        return R.ok().put("data", articleMapper.selectAll(type));
    }


    public R page(Integer page, Integer size, String searchValue
            , Integer cateId
            , Integer tagId) {
        Page<ArticleVO> page1 = new Page<>(page, size);
        List<ArticleVO> articleVOS = articleMapper.selectPageVo(page1, searchValue, cateId, tagId);
        page1.setRecords(articleVOS);
        return R.ok().put("data", page1);
    }


    public R cate_save(Integer articleId, String cateName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("article_id", articleId);
        Article article = this.getOne(queryWrapper);
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("cate_name", cateName);
        Category one = categoryMapper.selectOne(queryWrapper2);
        if(one == null){
            Category category = new Category();
            category.setCateName(cateName);
            categoryMapper.insert(category);
            article.setCateId(category.getCateId());
            this.saveOrUpdate(article);
        }
        if(one != null){
            article.setCateId(one.getCateId());
            this.saveOrUpdate(article);
        }
        return R.ok();
    }


    public R get(Integer articleId) {
        return R.ok().put("data", articleMapper.getById(articleId));
    }
}
