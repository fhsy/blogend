package top.faig.blog.article.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.faig.blog.article.service.IArticleService;
import top.faig.blog.article.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.faig.blog.common.type.ArticleType;
import top.faig.blog.common.utils.R;
import top.faig.blog.tags.service.ITagsService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fhs
 * 2020/5/12 11:06
 * 文件说明:
 */
@RestController
@RequestMapping("/base")
public class baseController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ITagsService tagsService;

    /**
     * @api {GET}  /base/info  首页信息
     * @apiGroup index
     * @apiSuccessExample {json} 成功
     * {
     * "msg": "success",
     * "code": 0,
     * "data": {
     * "articleConut": 5,
     * "tagsConut": 3
     * }
     * }
     */
    @GetMapping("/info")
    public R info() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state", ArticleType.RELEASE.getType());
        int articleConut = articleService.count(queryWrapper);
        int tagsConut = tagsService.count();
        Map map = new HashMap();
        map.put("articleConut", articleConut);
        map.put("tagsConut", tagsConut);
        return R.ok().put("data", map);
    }

}
