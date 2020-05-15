package top.faig.blog.article.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.faig.blog.article.service.IArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.faig.blog.common.utils.R;

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
     */
    @PostMapping("/stick")
    public R stick(Integer articleId, String tagNames) {
        return articleTagService.stick(articleId, tagNames);
    }


    /**
     * @api {GET}  /article-tag/getTagsByArticleId  查标签
     * @apiGroup article-tag
     * @apiSuccess {int} articleId 博文 id
     * @apiSuccessExample {json} 成功
     * {
     * "msg": "success",
     * "code": 0,
     * "list": [
     * {
     * "articleId": 49,
     * "tagId": 1,
     * "updateTime": "2020-04-30 18:26:01",
     * "createTime": "2020-04-30 18:26:01"
     * },
     * {
     * "articleId": 49,
     * "tagId": 3,
     * "updateTime": "2020-04-30 18:26:09",
     * "createTime": "2020-04-30 18:26:09"
     * }
     * ]
     * }
     */
    @GetMapping("/getTagsByArticleId")
    public R getTagsByArticleId(Integer articleId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("article_id", articleId);
        return R.ok().put("data", articleTagService.list(queryWrapper));
    }

}
