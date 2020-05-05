package com.monggo.service;

import com.monggo.common.utils.R;
import com.monggo.entity.ArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博文标签表 服务类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
public interface IArticleTagService extends IService<ArticleTag> {


    R stick(Integer articleId, String tagIds);
}
