package top.faig.blog.code.tags.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.faig.blog.code.tags.entity.Tags;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
public interface TagsMapper extends BaseMapper<Tags> {


    /**
     * 根据博文 ID 获得标签集合
     * @param article_id
     * @return
     */
    List<Tags> selectListByArticleId(Integer article_id);

}
