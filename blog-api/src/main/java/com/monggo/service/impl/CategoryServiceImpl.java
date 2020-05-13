package com.monggo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monggo.common.type.ArticleType;
import com.monggo.common.utils.R;
import com.monggo.entity.Article;
import com.monggo.entity.Category;
import com.monggo.mapper.CategoryMapper;
import com.monggo.service.IArticleService;
import com.monggo.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 类别表 服务实现类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private IArticleService articleService;

    @Override
    public R add(String name) {
        if (StringUtils.isEmpty(name)) {
            return R.error("分类名为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cate_name", name);
        Category category = this.getOne(queryWrapper);
        if (category != null) {
            return R.error("分类名已存在");
        }
        this.save(new Category().setCateName(name));
        return R.ok("添加成功");
    }

    @Override
    public R classCount() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state", ArticleType.RELEASE.getType());
        List<Article> articleList = articleService.list(queryWrapper);
        List<Category> categoryList = this.list();
        for (Category category : categoryList) {
            for (Article article : articleList) {
                if(article.getCateId() != null && article.getCateId().equals(category.getCateId())){
                    if(category.getCount() == null){
                        category.setCount(1);
                    } else {
                        category.setCount(category.getCount() + 1);
                    }
                }
            }
        }
        Collections.sort(categoryList, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                if(o1.getCount() == null){
                    o1.setCount(0);
                }
                if(o2.getCount() == null){
                    o2.setCount(0);
                }
                return o2.getCount() - o1.getCount();
            }
        });
        return R.ok().put("data", categoryList);
    }

}
