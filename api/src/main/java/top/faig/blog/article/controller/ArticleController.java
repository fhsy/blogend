package top.faig.blog.article.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.faig.blog.article.service.ArticleService;
import top.faig.blog.code.article.entity.Article;
import top.faig.blog.code.article.vo.ArticleVO;

import java.util.List;

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
@RequiredArgsConstructor
public class ArticleController {


    private final ArticleService articleService;


    /**
     * @api {POST}  /article/add  添加博文
     * @apiGroup article
     * @apiSuccess {String} title 标题
     * @apiSuccess {String} context 内容
     * @apiSuccess {int} cateId 分类 ID
     * @apiSuccess {String} state 状态   {RELEASE: 发布，DRAFT: 草稿 (默认)，RECOVERY: 回收}
     */
    @PostMapping("/add")
    public void add(Article article) {
        articleService.add(article);
    }


    /**
     * @api {GET}  /article/list  列表
     * @apiGroup article
     * @apiSuccess {int} type 模式  默认查发布和草稿 RELEASE： 发布 RECOVERY： 删除 DRAFT：草稿
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
    public List<ArticleVO> list(String type){
        return articleService.list(type);
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
     */
    @PostMapping("/save")
    public void save(Article article) {
        articleService.saveOrUpdate(article);
    }

    /**
     * @api {GET}  /article/index_list  首页列表
     * @apiGroup article
     * @apiSuccess {int} type 模式 {1：时间排，2：标签，3：分类}
     * @apiSuccess (可选参数) {int} val 搜索内容
     * @apiSuccess (可选参数) {int} valType 搜索模式 {1：标题搜，2：内容搜}
     * @apiSuccessExample {json} 成功
     * "contextList": [
     */
    @GetMapping("page")
    public Page<ArticleVO> page(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(value = "size", required = false, defaultValue = "8")  Integer size
            , String searchValue
            , Integer cateId
            , Integer tagId){
        System.out.println(123123123);
        return articleService.page(page, size, searchValue, cateId, tagId);
    }

    /**
     * @api {POST}  /article/cate_save  博文分类选择
     * @apiGroup article
     * @apiSuccess {int} articleId 博文ID
     * @apiSuccess {String} cateName 分类名
     * @apiSuccessExample {json} 成功
     * {
     * 	"msg": "success",
     * 	"code": 0
     * }
     */
    @PostMapping("/cate_save")
    public void cate_save(Integer articleId, String  cateName) {
        articleService.cate_save(articleId, cateName);
    }

    @GetMapping("/get/{articleId}")
    public ArticleVO get(@PathVariable Integer articleId){
       return articleService.get(articleId);
    }


}
