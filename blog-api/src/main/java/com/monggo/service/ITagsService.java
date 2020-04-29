package com.monggo.service;

import com.monggo.common.utils.R;
import com.monggo.entity.Tags;
import com.baomidou.mybatisplus.extension.service.IService;

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
