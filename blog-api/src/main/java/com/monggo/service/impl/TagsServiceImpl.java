package com.monggo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monggo.common.utils.R;
import com.monggo.entity.Category;
import com.monggo.entity.Tags;
import com.monggo.mapper.TagsMapper;
import com.monggo.service.ITagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements ITagsService {


    @Override
    public R add(String name) {
        if(StringUtils.isEmpty(name)){
            return R.error("标签名为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tag_name", name);
        Tags tags = this.getOne(queryWrapper);
        if(tags != null){
            return R.error("标签名已存在");
        }
        this.save(new Tags().setTagName(name));
        return R.ok("添加成功");
    }
}
