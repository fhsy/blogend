package top.faig.blog.article.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.faig.blog.code.article.entity.Category;
import top.faig.blog.article.service.CategoryService;

import java.util.List;

/**
 * <p>
 * 类别表 前端控制器
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * @api {POST}  /category/add  添加分类
     * @apiGroup category
     * @apiSuccess {String} name 分类名
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
    @PostMapping("add")
    public void add(String name) {
        categoryService.add(name);
    }


    /**
     * @api {GET}  /category/list  分类列表
     * @apiGroup category
     * @apiSuccessExample {json} 成功
     * {
     * "list": [
     * {
     * "cateId": 1,
     * "cateName": "123",
     * "updateTime": "2020-04-27 13:14:10",
     * "createTime": "2020-04-27 13:14:10"
     * }
     * ],
     * "code": 0
     * }
     */
    @GetMapping("list")
    public List<Category> list() {
         return categoryService.list();
    }

    /**
     * @api {GET}  /category/class-count  分类统计
     * @apiGroup index
     * @apiSuccessExample {json} 成功
     * {
     * "msg": "success",
     * "code": 0,
     * "data": [
     * {
     * "cateName": "分类",
     * "count": 2
     * },
     * {
     * "cateName": "lll",
     * "count": 1
     * }
     * ]
     * }
     */
    @GetMapping("class-count")
    public List<Category> classCount() {
        return categoryService.classCount();
    }
}
