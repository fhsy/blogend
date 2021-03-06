package top.faig.blog.tags;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.faig.blog.code.tags.entity.Tags;

import java.util.List;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagsController {

    private final TagsService tagsService;


    /**
     * @api {POST}  /tags/add  添加标签
     * @apiGroup tags
     * @apiSuccess {String} name 标签名
     * @apiSuccessExample {json} 成功
     * {
     * "msg": "添加成功",
     * "code": 0
     * }
     * @apiErrorExample {json} 失败
     * {
     * "msg": "标签名已存在",
     * "code": 500
     * }
     * <p>
     * {
     * "msg": "标签名为空",
     * "code": 500
     * }
     */
    @PostMapping("add")
    public void add(String name) {
         tagsService.add(name);
    }


    /**
     * @api {GET}  /tags/list  标签列表
     * @apiGroup tags
     * @apiSuccessExample {json} 成功
     * {
     * "data": [
     * {
     * "tagId": 1,
     * "tagName": "asd",
     * "updateTime": "2020-04-27 14:28:47",
     * "createTime": "2020-04-27 14:28:47"
     * }
     * ],
     * "code": 0
     * }
     * ],
     * "code": 0
     * }
     */
    @GetMapping("list")
    public List<Tags> list() {
        return tagsService.list();
    }




}
