package top.faig.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.faig.blog.common.type.ArticleType;
import top.faig.blog.common.utils.R;
import top.faig.blog.service.ArticleServiceImpl;
import top.faig.blog.service.TagsServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fhs
 * 2020/5/12 11:06
 * 文件说明:
 */
@RestController
@RequestMapping("/base")
@RequiredArgsConstructor
public class baseController {

    private final ArticleServiceImpl articleService;

    private final TagsServiceImpl tagsService;

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
