package com.monggo.service;

import com.monggo.common.utils.R;
import com.monggo.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

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
