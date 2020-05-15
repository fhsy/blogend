package top.faig.blog.tags.service;

import top.faig.blog.common.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import top.faig.blog.tags.entity.Tags;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
public interface ITagsService extends IService<Tags> {
    R add(String name);
}
