package top.faig.blog.article.service;


import top.faig.blog.common.utils.R;
import top.faig.blog.article.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 类别表 服务类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
public interface ICategoryService extends IService<Category> {

    R add(String name);

    R classCount();

}
