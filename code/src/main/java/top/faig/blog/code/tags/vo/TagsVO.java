package top.faig.blog.code.tags.vo;

import lombok.Data;
import top.faig.blog.code.article.entity.Article;

import java.io.Serializable;
import java.util.List;

/**
 * @author fhs
 * 2020/4/30 13:33
 * 文件说明:
 */
@Data
public class TagsVO implements Serializable{

    /**
     * 标签 ID
     */
    private Integer tagId;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 博文列表
     */
    private List<Article> articleList;

}
