package top.faig.blog.article.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.faig.blog.code.article.vo.InfoCountVO;
import top.faig.blog.common.type.ArticleType;
import top.faig.blog.article.service.ArticleService;
import top.faig.blog.tags.TagsService;


/**
 * @author fhs
 * 2020/5/12 11:06
 * 文件说明:
 */
@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

    private final ArticleService articleService;

    private final TagsService tagsService;

    /**
     * @api {GET}  /base/info  首页信息
     * @apiGroup index
     * @apiSuccessExample {json} 成功
     * {
     * "articleConut": 5,
     * "tagsConut": 3
     * }
     */
    @GetMapping("/info")
    public InfoCountVO info() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state", ArticleType.RELEASE.getType());
        Integer articleConut = articleService.count(queryWrapper);
        Integer tagsConut = tagsService.count();
        return new InfoCountVO(articleConut, tagsConut);
    }

}
