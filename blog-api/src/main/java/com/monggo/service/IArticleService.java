package com.monggo.service;

import com.monggo.common.utils.R;
import com.monggo.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 博文表 服务类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
public interface IArticleService extends IService<Article> {

    R add(Article article);

    R list(String type);

    R indexList(Integer page, Integer size);

}
