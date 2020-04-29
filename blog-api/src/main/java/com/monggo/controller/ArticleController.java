package com.monggo.controller;


import com.monggo.common.type.ArticleType;
import com.monggo.common.utils.R;
import com.monggo.entity.Article;
import com.monggo.service.IArticleService;
import com.monggo.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 博文表 前端控制器
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;


    /**
     * @api {POST}  /article/add  添加博文
     * @apiGroup article
     * @apiSuccess {String} title 标题
     * @apiSuccess {String} context 内容
     * @apiSuccess {int} cateId 分类 ID
     * @apiSuccess {String} state 状态   {RELEASE: 发布，DRAFT: 草稿 (默认)，RECOVERY: 回收}
     * @apiSuccessExample {json} 成功
     * {
     * "msg": "添加成功",
     * "code": 0
     * }
     * @apiErrorExample {json} 失败
     * {
     * "msg": "分类名已存在",
     * "code": 500
     * }
     * <p>
     * {
     * "msg": "分类名为空",
     * "code": 500
     * }
     */
    @PostMapping("/add")
    public R add(Article article) {
        return articleService.add(article);
    }

    /**
     * @api {POST}  /article/save  保存博文
     * @apiGroup article
     * @apiSuccess {id} articleId 博文 ID
     * @apiSuccess {String} title 标题
     * @apiSuccess {String} context 内容
     * @apiSuccess {int} cateId 分类 ID
     * @apiSuccess {String} state 状态   {RELEASE: 发布，DRAFT: 草稿 (默认)，RECOVERY: 回收}
     * @apiSuccessExample {json} 成功
     * {
     * "msg": "添加成功",
     * "code": 0
     * }
     * @apiErrorExample {json} 失败
     * {
     * "msg": "分类名已存在",
     * "code": 500
     * }
     * <p>
     * {
     * "msg": "分类名为空",
     * "code": 500
     * }
     */
    @PostMapping("/save")
    public R save(Article article) {
        articleService.saveOrUpdate(article);
        return R.ok("保存成功");
    }

    /**
     * @api {GET}  /article/search  搜索博文
     * @apiGroup article
     * @apiSuccess {String} val 搜索内容  标题，博文内容
     * @apiSuccess {int} type 搜索模式  默认查全部 1：标题 2 博文内容
     * @apiSuccessExample {json} 成功
     * {
     * "msg": "success",
     * "code": 0,
     * "contextList": [
     * {
     * "articleId": 1,
     * "title": "321",
     * "context": "32111",
     * "cateId": 1,
     * "state": "RECOVERY",
     * "updateTime": "2020-04-27 15:57:57",
     * "createTime": "2020-04-27 14:28:40"
     * }
     * ],
     * "titleList": null
     * }
     */
    @GetMapping("/search")
    public R search(String val, Integer type) {
        return articleService.search(val, type);
    }


    /**
     * @api {GET}  /article/list  列表
     * @apiGroup article
     * @apiSuccess {int} type 模式  默认查发布和草稿 1： 发布 2： 删除 3：草稿
     * @apiSuccessExample {json} 成功
     * "contextList": [
     * {
     * "articleId": 1,
     * "title": "321",
     * "context": "32111",
     * "cateId": 1,
     * "state": "RECOVERY",
     * "updateTime": "2020-04-27 15:57:57",
     * "createTime": "2020-04-27 14:28:40"
     * }
     */
    @GetMapping("list")
    public R list(Integer type){
        return articleService.list(type);
    }


}
