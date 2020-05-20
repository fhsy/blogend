package top.faig.blog.service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.faig.blog.code.entity.Tags;
import top.faig.blog.code.mapper.TagsMapper;
import top.faig.blog.common.utils.R;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags>{


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
