package top.faig.blog.tags;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.faig.blog.code.tags.entity.Tags;
import top.faig.blog.code.tags.mapper.TagsMapper;
import java.util.Optional;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@Service
public class TagsService extends ServiceImpl<TagsMapper, Tags>{


    public void add(String name) {
        Tags tags = this.getOne(Wrappers.<Tags>lambdaQuery()
                .eq(Tags::getTagName, name));
        Optional.ofNullable(tags)
                .filter(tag -> tag == null)
                .map(tag -> {
                    this.save(new Tags().setTagName(name));
                    return tag;
                });
    }
}
