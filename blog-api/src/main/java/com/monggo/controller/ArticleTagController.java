package com.monggo.controller;


import com.monggo.common.utils.R;
import com.monggo.entity.ArticleTag;
import com.monggo.service.IArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 博文标签表 前端控制器
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/article-tag")
public class ArticleTagController {

    @Autowired
    private IArticleTagService articleTagService;


    /**
     * @api {POST}  /article-tag/stick  贴标签
     * @apiGroup article-tag
     * @apiSuccess {int} articleId 博文 id
     * @apiSuccess {int} tagId 标签 id
     * @apiSuccessExample {json} 成功
     * {
     * "msg": "添加成功",
     * "code": 0
     * }
     * <p>
     * 再次请求为 取消
     * {
         * "msg": "取消成功",
         * "code": 0
     * }
     */
    @PostMapping("/stick")
    public R stick(ArticleTag articleTag) {
        return articleTagService.stick(articleTag);
    }


}
