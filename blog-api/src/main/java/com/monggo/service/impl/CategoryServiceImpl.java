package com.monggo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monggo.common.utils.R;
import com.monggo.entity.Category;
import com.monggo.mapper.CategoryMapper;
import com.monggo.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

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
    @Override
    public R add(String name) {
        if(StringUtils.isEmpty(name)){
            return R.error("分类名为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cate_name", name);
        Category category = this.getOne(queryWrapper);
        if(category != null){
            return R.error("分类名已存在");
        }
        this.save(new Category().setCateName(name));
        return R.ok("添加成功");
    }

}
